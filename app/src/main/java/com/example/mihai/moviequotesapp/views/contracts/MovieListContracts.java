package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;

import java.util.List;
import java.util.Map;

public interface MovieListContracts {

    interface View {
        void setPresenter(MovieListContracts.Presenter presenter);
        void showMovieList(List<String> movies, Map<String, List<Quote>> quotes);
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
