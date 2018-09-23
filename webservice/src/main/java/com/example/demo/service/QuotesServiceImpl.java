package com.example.demo.service;

import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.Quote;
import com.example.demo.models.QuoteDTO;
import com.example.demo.repositories.base.QuotesRepository;
import com.example.demo.service.base.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuotesServiceImpl implements QuotesService {

    private QuotesRepository repo;

    @Autowired
    public QuotesServiceImpl(QuotesRepository repo) {
        this.repo = repo;
    }

    @Override
    public void createQuote(QuoteDTO quoteDTO) throws SQLException {

        Quote quote = transformFromDTOToQuote(quoteDTO);

        Movie movie = quote.getMovie();
        Character quotedCharacter = quote.getQuotedCharacter();

        if (!isMovieExisting(movie)) {
            repo.insert(movie);
        }

        if (!isCharacterExisting(quotedCharacter)) {
            repo.insert(quotedCharacter);
        }

        int movieID = repo.getMovieID(movie);
        int characterID = repo.getCharacterID(quotedCharacter);
        movie.setId(movieID);
        quotedCharacter.setId(characterID);

        repo.insert(quote);
    }

    @Override
    public List<QuoteDTO> getQuotes(){
         List<Quote> quoteList =  repo.getQuotes();
        return transformFromQuoteToDTOList(quoteList);
    }

    @Override
    public QuoteDTO getQuoteById(int id){
        Quote quote =  repo.getQuotes().stream()
                .filter(x->x.getId()==id)
                .findFirst()
                .orElse(null);

        if (quote == null) {
            return null;
        }

        return new QuoteDTO(id, quote.getQuoteText(), quote.getMovie().getMovieName(),
                quote.getQuotedCharacter().getCharacterName(), quote.getRating());
    }

    @Override
    public void updateQuote(int id, QuoteDTO newQuoteDTO) throws SQLException {
        Quote newQuote = transformFromDTOToQuote(newQuoteDTO);

        Movie movie = newQuote.getMovie();
        Character quotedCharacter = newQuote.getQuotedCharacter();

        if (!isMovieExisting(movie)) {
            repo.insert(movie);
        }

        if (!isCharacterExisting(quotedCharacter)) {
            repo.insert(quotedCharacter);
        }

        int movieID = repo.getMovieID(movie);
        int characterID = repo.getCharacterID(quotedCharacter);
        movie.setId(movieID);
        quotedCharacter.setId(characterID);

        repo.updateQuote(id, newQuote);
    }

    @Override
    public void deleteQuote(int id) throws SQLException {
        repo.deleteQuote(id);
    }

    @Override
    public List<QuoteDTO> transformFromQuoteToDTOList(List<Quote> quoteList) {
        List<QuoteDTO> quoteDTOS = new ArrayList<>();

        for (Quote quote : quoteList) {
            QuoteDTO quoteDTO = new QuoteDTO(quote.getId(), quote.getQuoteText(),
                    quote.getMovie().getMovieName(), quote.getQuotedCharacter().getCharacterName(),
                    quote.getRating());

            quoteDTOS.add(quoteDTO);
        }

        return quoteDTOS;
    }

    @Override
    public Quote transformFromDTOToQuote(QuoteDTO quoteDTO) {
        Quote newQuote = new Quote();

        newQuote.setId(quoteDTO.getId());
        newQuote.setQuoteText(quoteDTO.getQuoteText());
        newQuote.setMovie(new Movie(quoteDTO.getMovie()));
        newQuote.setQuotedCharacter(new Character(quoteDTO.getQuotedCharacter()));
        newQuote.setRating(quoteDTO.getRating());
        return newQuote;
    }

    @Override
    public boolean isMovieExisting(Movie quoteMovie) {
        String movieName = quoteMovie.getMovieName();
        Movie movie = repo.getMovieByName(movieName);
        return movie != null;
    }

    @Override
    public boolean isCharacterExisting(Character quotedCharacter) {
        String quotedCharacterName = quotedCharacter.getCharacterName();
        Character character = repo.getCharacterByName(quotedCharacterName);
        return character != null;
    }
}
