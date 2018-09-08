package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.di.fragmentmodules.MovieListModule;
import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface MovieListContracts {

    interface View {
        void setPresenter(MovieListContracts.Presenter presenter);
        void showMovieList(List<String> movies, HashMap<String, List<Quote>> quotes);
        void showQuoteDetails(Quote quote);
        void showEmptyMovieList();
        void showLoading();
        void hideLoading();
        void showError(Exception e);
    }

    interface Presenter{
        void setView(MovieListContracts.View view);
        void loadMovies();
        void selectQuote(Quote quote);
    }
}
