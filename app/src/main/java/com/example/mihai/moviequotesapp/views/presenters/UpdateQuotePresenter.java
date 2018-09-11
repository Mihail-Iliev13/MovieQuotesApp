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
    private QuoteService mService;
    private Quote mClickedQuote;

    @Inject
    public UpdateQuotePresenter(QuoteService service, AsyncRunner asyncRunner){
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void generateQuote(String quoteText, String quoteMovie, String quotedCharacter, float quoteRating) throws IOException {
        Quote quoteToBeUpdated = new Quote(quoteText, quoteMovie, quotedCharacter, quoteRating);
        mAsyncRunner.runInBackground(() -> {
            try {
                mService.updateQuote(quoteToBeUpdated);
                mView.showToast();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                mView.showError(e);
            }
        });
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
     mView.changeButton();
    }

    @Override
    public void setClickedQuote(Quote quote) {
        mClickedQuote = quote;
    }
}
