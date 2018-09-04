package com.example.demo.service;

import com.example.demo.models.Quote;

import java.util.List;

public interface QuotesService {
    void createQuote(Quote quote);
    List<Quote> getQuotes();
    Quote getQuoteById(int id);
    void updateQuote(int id,Quote quote);
    void deleteQuote(int id);
}
