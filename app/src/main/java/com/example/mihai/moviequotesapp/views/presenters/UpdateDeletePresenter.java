package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteButtonContracts;

import java.io.IOException;
import java.util.List;
import java.util.Observer;

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

                Quote quote = mService.getQuoteByID(mSelectedQuoteID);
                mView.showUpdateActivity(quote);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void delete() {
                   mAsyncRunner.runInBackground(() -> {
            try {
                Quote quote = mService.getQuoteByID(mSelectedQuoteID);
                mService.deleteQuote(quote);
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
