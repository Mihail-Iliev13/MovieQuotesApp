package com.example.demo.repositories;

import com.example.demo.models.Quote;
import com.example.demo.repositories.base.QuotesRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemoryQuotesRepository implements QuotesRepository {

    private List<Quote> quotes = new ArrayList<>();

    public InMemoryQuotesRepository()

    {
        Quote quote = new Quote("I'll be back", "The Terminator",
                "Terminator", 5);
        createQuote(quote);
    }

    @Override
    public void createQuote(Quote quote) {

        quotes.add(quote);
    }

    @Override
    public List<Quote> getQuotes() {
        return quotes;
    }


    @Override
    public void updateQuote(int index, Quote newQuote) {
        quotes.set(index, newQuote);
    }


    @Override
    public void deleteQuote(Quote quoteToBeDeleted) {
        quotes.remove(quoteToBeDeleted);
    }

}
