package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.ListAllQuotesContracts;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class ListAllQuotesPresenter implements ListAllQuotesContracts.Presenter {

    private QuoteService mService;
    private AsyncRunner mAsyncRunner;
    private ListAllQuotesContracts.View mView;

    @Inject
    public ListAllQuotesPresenter(QuoteService service, AsyncRunner asyncRunner){
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void setView(ListAllQuotesContracts.View view) {
        mView = view;
    }

    @Override
    public void loadQuotes() {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes = mService.getAll();
                presentQuotesToView(quotes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void filterQuotes(String pattern) {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes = mService.getFilteredQuotes(pattern);
                presentQuotesToView(quotes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void selectQuotes(Quote quote) {

    }

    @Override
    public void presentQuotesToView(List<Quote> quotes) {
        mView.showQuotes(quotes);
    }
}
