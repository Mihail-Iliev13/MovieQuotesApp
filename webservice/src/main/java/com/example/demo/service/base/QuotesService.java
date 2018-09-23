package com.example.demo.service.base;

import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.Quote;
import com.example.demo.models.QuoteDTO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface QuotesService {
    void createQuote(QuoteDTO quoteDTO) throws SQLException;
    List<QuoteDTO> getQuotes() throws SQLException;
    QuoteDTO getQuoteById(int id) throws SQLException;
    void updateQuote(int id,QuoteDTO quote) throws SQLException;
    void deleteQuote(int id) throws SQLException;
    List<QuoteDTO> transformFromQuoteToDTOList(List<Quote> quoteList);
    Quote transformFromDTOToQuote(QuoteDTO quoteDTO);
    boolean isMovieExisting(Movie quoteMovie);
    boolean isCharacterExisting(Character quotedCharacter);
}
