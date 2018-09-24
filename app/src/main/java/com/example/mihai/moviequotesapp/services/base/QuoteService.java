package com.example.mihai.moviequotesapp.services.base;

import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QuoteService {

    List<Quote> getAll() throws IOException;

    void createQuote(Quote quote) throws IOException, IllegalArgumentException;

    void updateQuote(Quote quote) throws IOException, IllegalArgumentException;

    void deleteQuote(Quote quote) throws IOException;

    List<Quote> getFilteredQuotes(String pattern) throws IOException;

    List<String> getMoviesList() throws IOException;

    Map<String,List<Quote>> getQuotesByMovie() throws IOException;

    List<String> getFilteredMoviesList(String pattern) throws IOException;

    Map<String,List<Quote>> getFilteredQuotesByMovie(List<String> quoteList) throws IOException;

    Quote getQuoteByID(int mSelectedQuoteID) throws IOException;
}
