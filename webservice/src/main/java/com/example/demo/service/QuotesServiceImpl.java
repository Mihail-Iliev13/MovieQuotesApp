package com.example.demo.service;

import com.example.demo.models.Quote;
import com.example.demo.repositories.base.QuotesRepository;
import com.example.demo.service.base.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Service
public class QuotesServiceImpl implements QuotesService {

    private QuotesRepository repo;

    @Autowired
    public QuotesServiceImpl(QuotesRepository repo) {
        this.repo = repo;
    }

    @Override
    public void createQuote(Quote quote) throws SQLException {
        String quoteMovie = quote.getMovie();
        String quotedCharacter = quote.getQuotedCharacter();

        if (!isMovieExisting(quoteMovie)) {
            repo.insertIntoMoviesTable(quoteMovie);
        }

        if (!isCharacterExisting(quotedCharacter)) {
            repo.insertIntoCharactersTable(quotedCharacter);
        }

        repo.insertIntoQuotesTable(quote);
    }

    @Override
    public List<Quote> getQuotes() throws SQLException {
        ResultSet resultSet =  repo.getQuotes();
        List<Quote> quoteList = new ArrayList<>();
        while (resultSet.next()) {
            int quoteID = resultSet.getInt(1);
            String quoteBody = resultSet.getString(2);
            String quoteMovie = resultSet.getString(3);
            String quotedCharacter = resultSet.getString(4);
            float rating = resultSet.getFloat(5);
            Quote currentQuote = new Quote(quoteID, quoteBody, quoteMovie, quotedCharacter, rating);
            quoteList.add(currentQuote);
        }
        return quoteList;
    }

    @Override
    public Quote getQuoteById(int id) throws SQLException {
        return getQuotes().stream()
                .filter(x->x.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateQuote(int id, Quote newQuote) throws SQLException {
        String movie = newQuote.getMovie();
        String character = newQuote.getQuotedCharacter();

        if (!isMovieExisting(newQuote.getMovie())) {
            repo.insertIntoMoviesTable(movie);
        }

        if (!isCharacterExisting(character)) {
            repo.insertIntoCharactersTable(character);
        }

        repo.updateQuote(id, newQuote);
    }

    @Override
    public void deleteQuote(int id) throws SQLException {
        repo.deleteQuote(id);
    }

    private boolean isMovieExisting(String quoteMovie) throws SQLException {
        HashSet<String> existingMovies = new HashSet<>() ;
        ResultSet resultSet = repo.getMovies();
        while (resultSet.next()) {
            existingMovies.add(resultSet.getString(1));
        }

        return existingMovies.contains(quoteMovie);
    }

    private boolean isCharacterExisting(String character) throws SQLException {
        HashSet<String> existingMovies = new HashSet<>() ;
        ResultSet resultSet = repo.getCharacters();
        while (resultSet.next()) {
            existingMovies.add(resultSet.getString(1));
        }

        return existingMovies.contains(character);
    }
}
