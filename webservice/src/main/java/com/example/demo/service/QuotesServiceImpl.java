package com.example.demo.service;

import com.example.demo.models.Quote;
import com.example.demo.repositories.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuotesServiceImpl implements QuotesService {
    private QuotesRepository repo;
@Autowired
    public QuotesServiceImpl(QuotesRepository repo) {
        this.repo = repo;
    }

    @Override
    public void createQuote(Quote quote) {
repo.createQuote(quote);
    }

    @Override
    public List<Quote> getQuotes() {
        return repo.getQuotes();
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
