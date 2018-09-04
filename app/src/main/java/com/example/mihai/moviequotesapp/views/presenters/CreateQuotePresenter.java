package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;

import java.io.IOException;

public class CreateQuotePresenter implements GenerateQuoteContracts.Presenter {

    GenerateQuoteContracts.View mView;
    QuoteService mService;

    @Override
    public void generateQuote() throws IOException {
        String quoteText = mView.getQuoteBody();
        String quoteMovie = mView.getQuoteMovie();
        String quotedCharacter = mView.getQuotedCharacter();
        float quoteRating = mView.getRating();

        Quote newQuote = new Quote(quoteText, quoteMovie, quotedCharacter, quoteRating);
        mService.createQuote(newQuote);
    }

    @Override
    public void setView(GenerateQuoteContracts.View view) {
       this.mView = view;
    }
}
