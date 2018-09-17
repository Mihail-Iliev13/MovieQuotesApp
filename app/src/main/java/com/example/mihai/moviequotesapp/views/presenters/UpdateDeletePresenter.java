package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteButtonContracts;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class UpdateDeletePresenter implements UpdateDeleteButtonContracts.Presenter {

    private final AsyncRunner mAsyncRunner;
    private final QuoteService mService;
    private UpdateDeleteButtonContracts.View mView;
    private int mSelectedQuoteID;

    @Inject
    public UpdateDeletePresenter (QuoteService service, AsyncRunner asyncRunner) {
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void update() {
        mAsyncRunner.runInBackground(() -> {
            try {

                List<Quote> quoteList = mService.getAll();
                for (Quote quote : quoteList) {
                    if (quote.getId() == mSelectedQuoteID) {
                        mView.showUpdateActivity(quote);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void delete() {
                   mAsyncRunner.runInBackground(() -> {
            try {

                List<Quote> quoteList = mService.getAll();
                for (Quote quote : quoteList) {
                    if (quote.getId() == mSelectedQuoteID) {
                        mService.deleteQuote(quote);
                        break;
                    }
                }
                mView.endActivity();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setView(UpdateDeleteButtonContracts.View view) {
        this.mView = view;
    }

    @Override
    public void setSelectedQuoteID(int id) {
        this.mSelectedQuoteID = id;
    }

}
