package com.example.mihai.moviequotesapp.di.presentermodules;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.httprequesters.OkHttpRequester;
import com.example.mihai.moviequotesapp.httprequesters.base.HttpRequester;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.presenters.CreateQuotePresenter;
import com.example.mihai.moviequotesapp.views.presenters.UpdateQuotePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UpdatePresenterModule {
    @Provides
    public GenerateQuoteContracts.UpdatePresenter updatePresenter(QuoteService service, AsyncRunner asyncRunner) {
        return new UpdateQuotePresenter(service, asyncRunner);
    }
}