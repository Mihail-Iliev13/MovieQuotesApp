package com.example.mihai.moviequotesapp.services;

import android.os.Build;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.repositories.base.Repository;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.validators.base.Validator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HttpService implements QuoteService {

    private Repository mRepository;
    private Validator mValidator;

    public HttpService (Repository repository, Validator validator) {
        this.mRepository = repository;
        this.mValidator = validator;
    }

    @Override
    public List<Quote> getAll() throws IOException {
        List<Quote> quotesList =  mRepository.getAll();
        Collections.sort(quotesList, Collections.reverseOrder());
        return quotesList;
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

    @Override
    public List<Quote> getFilteredQuotes(String pattern) throws IOException {
        String patternToLower = pattern.toLowerCase();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getAll().stream()
                    .filter(quote -> quote.getText().toLowerCase().contains(patternToLower))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
