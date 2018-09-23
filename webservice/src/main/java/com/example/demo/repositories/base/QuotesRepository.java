package com.example.demo.repositories.base;

import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.Quote;

import java.sql.SQLException;
import java.util.List;

public interface QuotesRepository {
    List<Quote> getQuotes();
    void updateQuote(int index, Quote newQuote) throws SQLException;
    void deleteQuote(int quoteID) throws SQLException;
    void insert(Object object) throws SQLException;
    int getMovieID(Movie movie);
    int getCharacterID(Character quotedCharacter);
    Movie getMovieByName(String movieName);
    Character getCharacterByName(String quotedCharacterName);
}
