package com.example.mihai.moviequotesapp.di.presentermodules;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.contracts.ListAllQuotesContracts;
import com.example.mihai.moviequotesapp.views.presenters.ListAllQuotesPresenter;
import com.example.mihai.moviequotesapp.views.presenters.UpdateQuotePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ListPresenterModule {
    @Provides
    public ListAllQuotesContracts.Presenter listPresenter(QuoteService service, AsyncRunner asyncRunner) {
        return new ListAllQuotesPresenter(service, asyncRunner);
    }
}
