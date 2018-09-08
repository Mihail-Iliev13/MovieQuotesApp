package com.example.demo.repositories;

import com.example.demo.models.Quote;

import java.util.HashMap;
import java.util.List;

public interface QuotesRepository {
    void createQuote(Quote quote);
    List<Quote> getQuotes();
    Quote getQuoteById(int id);
    void updateQuote(int id,Quote quote);
    void deleteQuote(int id);
    HashMap<String, List<Quote>> getMovies();
}
