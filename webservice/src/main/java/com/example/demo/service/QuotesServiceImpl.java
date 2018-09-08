package com.example.demo.service;

import com.example.demo.models.Quote;
import com.example.demo.repositories.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class QuotesServiceImpl implements QuotesService {

    private QuotesRepository repo;
    private static final int STARTING_ID = 1;
    private int id;

    @Autowired
    public QuotesServiceImpl(QuotesRepository repo) {
        this.repo = repo;
        id = STARTING_ID;
    }

    @Override
    public void createQuote(Quote quote) {
        quote.setId(id);
        id++;
        repo.createQuote(quote);
    }

    @Override
    public List<Quote> getQuotes() {
        return repo.getQuotes();
    }

    @Override
    public HashMap<String, List<Quote>> getMovies() {
        return repo.getMovies();
    }

    @Override
    public Quote getQuoteById(int id) {
        return repo.getQuoteById(id);
    }

    @Override
    public void updateQuote(int id, Quote quote) {
    repo.updateQuote(id, quote);
    }

@Override
public void deleteQuote(int id) {
repo.deleteQuote(id);
    }
}
