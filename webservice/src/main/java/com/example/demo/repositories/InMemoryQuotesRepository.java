package com.example.demo.repositories;

import com.example.demo.models.Quote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
public class InMemoryQuotesRepository implements QuotesRepository {

    private List<Quote> quotes = new ArrayList<>();
    private HashMap<String, List<Quote>> movies = new HashMap<String, List<Quote>>();

    public InMemoryQuotesRepository()

    {
        Quote quote = new Quote("I'll be back", "The Terminator",
                "Terminator", 5);
        createQuote(quote);
    }

    @Override
    public void createQuote(Quote quote) {
        quotes.add(quote);

        String currentMovie = quote.getMovie();
        putInMap(quote, currentMovie);
    }

    private void putInMap(Quote quote, String currentMovie) {
        if(!movies.keySet().contains(currentMovie)) {
            movies.put(currentMovie, new ArrayList<>());
        }

        movies.get(currentMovie).add(quote);
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
    public void updateQuote(int id, Quote newQuote) {
        Quote quoteToBeUpdated = getQuoteById(id);
        newQuote.setId(quoteToBeUpdated.getId());
        updateInList(newQuote, quoteToBeUpdated);
        updateInMap(newQuote, quoteToBeUpdated);
    }

    private void updateInList(Quote newQuote, Quote quoteToBeUpdated) {
        int indexInAllQuotes = quotes.indexOf(quoteToBeUpdated);
        quotes.set(indexInAllQuotes, newQuote);
    }

    private void updateInMap(Quote updatedQuote, Quote quoteToBeUpdated) {

        if (isMovieFieldUpdated(updatedQuote, quoteToBeUpdated)) {
            transferInMap(updatedQuote, quoteToBeUpdated);

            if (movies.get(quoteToBeUpdated.getMovie()).isEmpty()) {
                movies.remove(quoteToBeUpdated.getMovie());
            }

        } else {
            String movie = quoteToBeUpdated.getMovie();
            List<Quote> quotesList = movies.get(movie);
            int indexInMap = quotesList.indexOf(quoteToBeUpdated);
            quotesList.set(indexInMap, updatedQuote);
        }
    }

    @Override
    public void deleteQuote(int id) {
        Quote quoteTOBeDeleted = getQuoteById(id);
        deleteInList(quoteTOBeDeleted, quotes);
        deleteInMap(quoteTOBeDeleted);
    }

    @Override
    public HashMap<String, List<Quote>> getMovies() {
        return movies;
    }

    private void deleteInMap(Quote quoteTOBeDeleted) {
        String movie = quoteTOBeDeleted.getMovie();
        List<Quote> quotesList = movies.get(movie);
        deleteInList(quoteTOBeDeleted, quotesList);

        if (quotesList.isEmpty()) {
            movies.remove(movie);
        }
    }

    private void removeFromMap(Quote quoteToBeUpdated) {
        movies.get(quoteToBeUpdated.getMovie()).remove(quoteToBeUpdated);
    }

    private boolean isMovieFieldUpdated(Quote newQuote, Quote quoteToBeUpdated) {
        return !quoteToBeUpdated.getMovie().equals(newQuote.getMovie());
    }

    private void deleteInList(Quote quoteTOBeDeleted, List<Quote> quotes) {
        quotes.remove(quoteTOBeDeleted);
    }

    private void transferInMap(Quote updatedQuote, Quote quoteToBeUpdated) {
        removeFromMap(quoteToBeUpdated);
        putInMap(updatedQuote, updatedQuote.getMovie());
    }
}
