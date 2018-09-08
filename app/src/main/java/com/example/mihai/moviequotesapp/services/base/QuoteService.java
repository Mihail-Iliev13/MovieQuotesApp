package com.example.mihai.moviequotesapp.services.base;

import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface QuoteService {

    List<Quote> getAll() throws IOException;

    void createQuote(Quote quote) throws IOException, IllegalArgumentException;

    void updateQuote(Quote quote) throws IOException, IllegalArgumentException;

    void deleteQuote(Quote quote) throws IOException;

    List<Quote> getFilteredQuotes(String pattern) throws IOException;

    List<String> getMoviesList() throws IOException;

    HashMap<String,List<Quote>> getQuotesByMovie() throws IOException;

}
