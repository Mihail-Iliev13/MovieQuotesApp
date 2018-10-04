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
    private Quote mSelectedQuote;

    @Inject
    public UpdateQuotePresenter(QuoteService service, AsyncRunner asyncRunner){
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void generateQuote(String quoteText, String quoteMovie, String quotedCharacter, float quoteRating) throws IOException {

        mSelectedQuote.quoteText = quoteText;
        mSelectedQuote.movie = quoteMovie;
        mSelectedQuote.quotedCharacter = quotedCharacter;
        mSelectedQuote.rating = quoteRating;

        mAsyncRunner.runInBackground(() -> {
            try {
                mService.updateQuote(mSelectedQuote);
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
     mView.setQuoteBody(mSelectedQuote.getText());
     mView.setQuoteMovie(mSelectedQuote.getMovie());
     mView.setQuotedCharacter(mSelectedQuote.getQuotedCharacter());
     mView.setRating(mSelectedQuote.getRating());
     mView.changeButton();
    }

    @Override
    public void setSelectedQuote(Quote quote) {
        mSelectedQuote = quote;
    }

}
