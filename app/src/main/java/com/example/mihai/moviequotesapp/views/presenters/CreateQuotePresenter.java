package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;

import java.io.IOException;

import javax.inject.Inject;

public class CreateQuotePresenter implements GenerateQuoteContracts.Presenter {

   private GenerateQuoteContracts.View mView;
   private AsyncRunner mAsyncRunner;
   public QuoteService mService;

    @Inject
    public CreateQuotePresenter(QuoteService service, AsyncRunner asyncRunner){
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void generateQuote() throws IOException {
        String quoteText = mView.getQuoteBody();
        String quoteMovie = mView.getQuoteMovie();
        String quotedCharacter = mView.getQuotedCharacter();
        float quoteRating = mView.getRating();

        Quote newQuote = new Quote(quoteText, quoteMovie, quotedCharacter, quoteRating);
        mAsyncRunner.runInBackground(() -> {
            try {
                mService.createQuote(newQuote);
                mView.showToast();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setView(GenerateQuoteContracts.View view) {
       this.mView = view;
    }

    @Override
    public void changeRatingBarColor() {
        mView.setRatingBarColorToYellow();
    }
}
