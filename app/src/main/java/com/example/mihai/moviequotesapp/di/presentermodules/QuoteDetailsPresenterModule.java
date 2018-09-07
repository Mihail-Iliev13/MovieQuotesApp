package com.example.mihai.moviequotesapp.di.presentermodules;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.contracts.QuoteDetailsContracts;
import com.example.mihai.moviequotesapp.views.presenters.QuoteDetailsPresenter;
import com.example.mihai.moviequotesapp.views.presenters.UpdateQuotePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class QuoteDetailsPresenterModule {
    @Provides
    public QuoteDetailsContracts.Presenter quoteDetailsPresenter(QuoteService service, AsyncRunner asyncRunner) {
        return new QuoteDetailsPresenter(service, asyncRunner);
    }
}
