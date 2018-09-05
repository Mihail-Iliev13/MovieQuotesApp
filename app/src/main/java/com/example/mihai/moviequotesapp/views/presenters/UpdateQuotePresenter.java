package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;

import java.io.IOException;

import javax.inject.Inject;

public class UpdateQuotePresenter implements GenerateQuoteContracts.UpdatePresenter {

    private GenerateQuoteContracts.View mView;
    private AsyncRunner mAsyncRunner;
    public QuoteService mService;
    private Quote mClickedQuote;

    @Inject
    public UpdateQuotePresenter(QuoteService service, AsyncRunner asyncRunner){
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void generateQuote() throws IOException {
        String quoteText = mView.getQuoteBody();
        String quoteMovie = mView.getQuoteMovie();
        String quotedCharacter = mView.getQuotedCharacter();
        float quoteRating = mView.getRating();

        Quote updatedQuote = new Quote(quoteText, quoteMovie, quotedCharacter, quoteRating);
        updatedQuote.setId(mClickedQuote.getId());
        mService.updateQuote(updatedQuote);
    }

    @Override
    public void setView(GenerateQuoteContracts.View view) {
        this.mView = view;
    }

    @Override
    public void fillFields() {
     mView.setQuoteBody(mClickedQuote.getText());
     mView.setQuoteMovie(mClickedQuote.getMovie());
     mView.setQuotedCharacter(mClickedQuote.getQuotedCharacter());
     mView.setRating(mClickedQuote.getRating());
     mView.makeButtonBlue();
    }

    @Override
    public void setClickedQuote(Quote quote) {
        mClickedQuote = quote;
    }
}
