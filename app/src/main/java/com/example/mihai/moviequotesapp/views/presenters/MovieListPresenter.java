package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.MovieListContracts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MovieListPresenter implements MovieListContracts.Presenter {

    private QuoteService mService;
    private AsyncRunner mAsyncRunner;
    private MovieListContracts.View mView;

    @Inject
    public MovieListPresenter(QuoteService mService, AsyncRunner mAsyncRunner) {
        this.mService = mService;
        this.mAsyncRunner = mAsyncRunner;
    }

    @Override
    public void setView(MovieListContracts.View view) {
        mView = view;
    }

    @Override
    public void loadMovies() {
        mView.showLoading();
        mAsyncRunner.runInBackground(() -> {
            try {
                List<String>  headers = mService.getMoviesList();
                Map<String, List<Quote>> quotes = mService.getQuotesByMovie();
                mView.showMovieList(headers, quotes);

                if (headers.isEmpty()) {
                    mView.showEmptyMovieList();
                }
            } catch (IOException e) {
                e.printStackTrace();
                mView.showError(e);
            }
            mView.hideLoading();
        });
    }

    @Override
    public void selectQuote(Quote quote) {
        mView.showQuoteDetails(quote);
    }

    @Override
    public void navigateToUpdate(Quote quote) {
        mView.showUpdateActivity(quote);
    }

    @Override
    public void deleteQuote(Quote quote) {
        mAsyncRunner.runInBackground(() -> {
            try {
                mService.deleteQuote(quote);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void filterQuotes(String pattern) {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<String> headers = mService.getFilteredMoviesList(pattern);
                Map<String, List<Quote>> quotes = mService.getFilteredQuotesByMovie(headers);
                mView.showMovieList(headers, quotes);
                if (headers.isEmpty()) {
                    mView.showEmptyMovieList();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
