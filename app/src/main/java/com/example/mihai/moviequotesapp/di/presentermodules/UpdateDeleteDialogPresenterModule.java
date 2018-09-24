package com.example.mihai.moviequotesapp.di.presentermodules;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteDialogContracts;
import com.example.mihai.moviequotesapp.views.presenters.UpdateDeleteDialogPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UpdateDeleteDialogPresenterModule {
    @Provides
    public UpdateDeleteDialogContracts.Presenter presenter
            (QuoteService service, AsyncRunner asyncRunner) {
        return new UpdateDeleteDialogPresenter(service, asyncRunner);
    }
}