package com.example.demo.repositories;

import com.example.demo.models.Quote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemoryQuotesRepository implements QuotesRepository {

    private List<Quote> quotes= new ArrayList<>();

    public InMemoryQuotesRepository()
    {
        quotes.add(new Quote("I'll be back","The Terminator","Terminator",5));
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
    public Quote getQuoteById(int id) {
        return quotes
                .stream()
                .filter(x->x.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateQuote(int id, Quote quote) {
        Quote quote1 = getQuoteById(id);
        int index = quotes.indexOf(quote1);
        quotes.set(index,quote);
    }

    @Override
    public void deleteQuote(int id) {
        quotes.remove(id);
    }
}
