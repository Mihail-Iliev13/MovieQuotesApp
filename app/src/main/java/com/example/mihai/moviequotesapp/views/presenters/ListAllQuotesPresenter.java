package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.QuotesListContracts;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

public class ListAllQuotesPresenter implements QuotesListContracts.Presenter {

    private QuoteService mService;
    private AsyncRunner mAsyncRunner;
    private QuotesListContracts.View mListView;

    @Inject
    public ListAllQuotesPresenter(QuoteService service, AsyncRunner asyncRunner){
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void setView(QuotesListContracts.View view) {
        mListView = view;
    }

    @Override
    public void loadItems() {
        mListView.showLoading();
        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes = mService.getAll();
                mListView.showQuotes(quotes);

                if (quotes.isEmpty()) {
                    mListView.showEmptyQuotesList();
                }

            } catch (IOException e) {
                e.printStackTrace();
                mListView.showError(e);
            }
            mListView.hideLoading();
        });

    }

    @Override
    public void filterQuotes(String pattern) {

        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes = mService.getFilteredQuotes(pattern);
                mListView.showQuotes(quotes);
            } catch (IOException e) {
                e.printStackTrace();
                mListView.showError(e);
            }
        });
    }

    @Override
    public void selectQuote(Quote quote) {
        mListView.showQuoteDetails(quote);
    }

    @Override
    public void selectOnLong(Quote quote) {
        mListView.showDialogBox(quote);
    }
}
