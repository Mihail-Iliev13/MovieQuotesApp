package com.example.mihai.moviequotesapp.services.base;

import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;
import java.util.List;

public interface QuoteService {

    List<Quote> getAll() throws IOException;

    void createQuote(Quote quote) throws IOException;

    void updateQuote(Quote quote) throws IOException;

    void deleteQuote(Quote quote) throws IOException;

}
