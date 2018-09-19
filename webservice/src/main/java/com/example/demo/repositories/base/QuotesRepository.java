package com.example.demo.repositories.base;

import com.example.demo.models.Quote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface QuotesRepository {
    List<Quote> getQuotes();
    Set<String> getMovies();
    Set<String> getCharacters();
    void updateQuote(int index, Quote quote) throws SQLException;
    void deleteQuote(int quoteID) throws SQLException;
    int getMovieID(String movie) throws SQLException;
    int getCharacterID(String quotedCharacter) throws SQLException;
    void insertIntoQuotesTable(Quote quote) throws SQLException;
    void insertIntoCharactersTable(String character) throws SQLException;
    void insertIntoMoviesTable(String movie) throws SQLException;
}
