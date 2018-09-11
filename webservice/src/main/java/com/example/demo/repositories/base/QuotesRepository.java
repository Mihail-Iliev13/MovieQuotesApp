package com.example.demo.repositories.base;

import com.example.demo.models.Quote;

import java.util.HashMap;
import java.util.List;

public interface QuotesRepository {
    void createQuote(Quote quote);
    List<Quote> getQuotes();
    void updateQuote(int index, Quote quote);
    void deleteQuote(Quote quoteToBeDeleted);
}
