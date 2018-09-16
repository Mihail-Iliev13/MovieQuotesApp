package com.example.mihai.moviequotesapp.services;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.repositories.base.Repository;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.validators.base.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void createQuote(Quote quote) throws IOException, IllegalArgumentException {

        if (!mValidator.isValid(quote)) {
            throw new IllegalArgumentException("All fields must be filled!");
        } else {
            mRepository.add(quote);
        }
    }

    @Override
    public void updateQuote(Quote quote) throws IOException, IllegalArgumentException {

        if (!mValidator.isValid(quote)) {
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
        List<Quote> quotes = getAll();
        List<Quote> filteredQuotes = new ArrayList<>();

        for (Quote quote: quotes) {
            if (quote.getText().toLowerCase().contains(patternToLower)) {
                filteredQuotes.add(quote);
            }
        }
        return filteredQuotes;
    }

    @Override
    public List<String> getMoviesList() throws IOException {
        List<Quote> allQuotes = mRepository.getAll();
        Set<String> moviesSet = new HashSet<>();

        for (Quote quote : allQuotes) {
            moviesSet.add(quote.getMovie());
        }

        List<String> moviesList = new ArrayList<>(moviesSet);
        Collections.sort(moviesList);
        return moviesList;
    }

    @Override
    public Map<String, List<Quote>> getQuotesByMovie() throws IOException {
        List<Quote> allQuotes =  mRepository.getAll();
        Map<String, List<Quote>> allQuotesByMovies = new HashMap<>();

        for (Quote quote: allQuotes) {

            if (!allQuotesByMovies.containsKey(quote.movie)) {
                allQuotesByMovies.put(quote.getMovie(), new ArrayList<>());
            }

            allQuotesByMovies.get(quote.getMovie()).add(quote);
        }

        return allQuotesByMovies;
    }
}
