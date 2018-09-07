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
        mView.showLoading();
        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes = mService.getAll();

                    mView.showQuotes(quotes);

                if (quotes.isEmpty()) {
                    mView.showEmptyQuotesList();
                }

            } catch (IOException e) {
                e.printStackTrace();
                mView.showError(e);
            }
            mView.hideLoading();
        });
    }

    @Override
    public void filterQuotes(String pattern) {

        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes = mService.getFilteredQuotes(pattern);
                mView.showQuotes(quotes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void selectQuote(Quote quote) {
        mView.showQuoteDetails(quote);
    }

    @Override
    public void selectOnLong(Quote quote) {
        mView.showDialogBox();
    }

    @Override
    public void deleteQuote(Quote quote) {

        mAsyncRunner.runInBackground(() -> {
            try {
                mService.deleteQuote(quote);
                mView.showToast();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void navigateToUpdate() {
        mView.showUpdateActivity();
    }
}
