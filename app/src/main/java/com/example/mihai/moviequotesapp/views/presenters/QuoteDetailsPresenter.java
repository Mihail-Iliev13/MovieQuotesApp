package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.activities.QuoteDetailsActivity;
import com.example.mihai.moviequotesapp.views.contracts.QuoteDetailsContracts;

import java.io.IOException;

import javax.inject.Inject;

public class QuoteDetailsPresenter implements QuoteDetailsContracts.Presenter{

    private  QuoteService mQuotesService;
    private  AsyncRunner mAsyncRunner;
    private QuoteDetailsContracts.View mView;
    private Quote mSelectedQuote;

    @Inject
    public QuoteDetailsPresenter( QuoteService quotesService, AsyncRunner asyncRunner) {
        mQuotesService = quotesService;
        mAsyncRunner = asyncRunner;
    }

    @Override
    public void setView(QuoteDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void setSelectedQuote(Quote quote) {
        mSelectedQuote = quote;
    }

    @Override
    public void loadQuote() {
        mView.showQuote(mSelectedQuote);
    }
}
