package com.example.mihai.moviequotesapp.services;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.repositories.base.Repository;
import com.example.mihai.moviequotesapp.services.base.QuoteService;

import java.io.IOException;
import java.util.List;

public class HttpService implements QuoteService {

    Repository<Quote> repository;

    @Override
    public List<Quote> getAll() throws IOException {
        return repository.getAll();
    }

    @Override
    public void createQuote(Quote quote) throws IOException {
        repository.add(quote);
    }

    @Override
    public void updateQuote(Quote quote) throws IOException {
        int id = quote.getId();
        repository.update(id, quote);
    }

    @Override
    public void deleteQuote(Quote quote) throws IOException {
        int id = quote.getId();
        repository.delete(id, quote);
    }
}
