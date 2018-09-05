package com.example.mihai.moviequotesapp.services;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.repositories.base.Repository;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.validators.base.Validator;

import java.io.IOException;
import java.util.List;

public class HttpService implements QuoteService {

    private Repository mRepository;
    private Validator mValidator;

    public HttpService (Repository repository, Validator validator) {
        this.mRepository = repository;
        this.mValidator = validator;
    }

    @Override
    public List<Quote> getAll() throws IOException {
        return mRepository.getAll();
    }

    @Override
    public void createQuote(Quote quote) throws IOException {

        if (!mValidator.isValid(quote)) {
            throw new IllegalArgumentException("Quote is invalid!!!");
        } else {
            mRepository.add(quote);
        }
    }

    @Override
    public void updateQuote(Quote quote) throws IOException {

        if (mValidator.isValid(quote)) {
            throw new IllegalArgumentException("Quote is invalid!!!");
        } else {
            int id = quote.getId();
            mRepository.update(id, quote);
        }
    }

    @Override
    public void deleteQuote(Quote quote) throws IOException {
        int id = quote.getId();
        mRepository.delete(id, quote);
    }
}
