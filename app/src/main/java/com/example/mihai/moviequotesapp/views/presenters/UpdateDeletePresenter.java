package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteButtonContracts;

import java.io.IOException;

import javax.inject.Inject;

public class UpdateDeletePresenter implements UpdateDeleteButtonContracts.Presenter {

    private final AsyncRunner mAsyncRunner;
    private final QuoteService mService;
    private UpdateDeleteButtonContracts.View mView;

    @Inject
    public UpdateDeletePresenter (QuoteService service, AsyncRunner asyncRunner) {
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void update() {
        mView.showUpdateActivity();
    }

    @Override
    public void delete(Quote quote) {
        mAsyncRunner.runInBackground(() -> {
            try {
                mService.deleteQuote(quote);
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
    public void navigateToUpdate() {
        mView.showUpdateActivity();
    }
}
