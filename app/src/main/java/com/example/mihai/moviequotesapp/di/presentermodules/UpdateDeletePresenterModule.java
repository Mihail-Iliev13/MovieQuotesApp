package com.example.mihai.moviequotesapp.di.presentermodules;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteButtonContracts;
import com.example.mihai.moviequotesapp.views.presenters.UpdateDeletePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UpdateDeletePresenterModule {
    @Provides
    public UpdateDeleteButtonContracts.Presenter updateDelete
            (QuoteService service, AsyncRunner asyncRunner) {
        return new UpdateDeletePresenter(service, asyncRunner);
    }
}
