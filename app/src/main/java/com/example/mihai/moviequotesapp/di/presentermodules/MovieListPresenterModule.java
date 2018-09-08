package com.example.mihai.moviequotesapp.di.presentermodules;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.MovieListContracts;
import com.example.mihai.moviequotesapp.views.presenters.MovieListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListPresenterModule {

@Provides
public MovieListContracts.Presenter movieListPresenter(QuoteService service, AsyncRunner asyncRunner){
    return new MovieListPresenter(service,asyncRunner);
}
}
