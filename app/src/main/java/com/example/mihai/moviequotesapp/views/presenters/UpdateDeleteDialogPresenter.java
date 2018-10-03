package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteButtonContracts;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteDialogContracts;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class UpdateDeleteDialogPresenter implements UpdateDeleteDialogContracts.Presenter {

    private final AsyncRunner mAsyncRunner;
    private final QuoteService mService;
    private UpdateDeleteDialogContracts.View mView;
    private int mSelectedQuoteID;

    @Inject
    public UpdateDeleteDialogPresenter (QuoteService service, AsyncRunner asyncRunner) {
        this.mService = service;
        this.mAsyncRunner = asyncRunner;
    }

    @Override
    public void update() {
        mAsyncRunner.runInBackground(() -> {
            try {

                Quote quote = mService.getQuoteByID(mSelectedQuoteID);
                mView.showUpdateActivity(quote);

            } catch (IOException e) {
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void setView(UpdateDeleteDialogContracts.View view) {
        this.mView = view;
    }

    @Override
    public void setSelectedQuoteID(int id) {
        this.mSelectedQuoteID = id;
    }

}
