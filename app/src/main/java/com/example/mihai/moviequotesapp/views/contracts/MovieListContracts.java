package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.baseListContracts.BaseListPresenter;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteDialogFragment;

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
        void showDialogBox(Quote quote);
        void setDialog(UpdateDeleteDialogFragment dialogFragment);
    }

    interface Presenter extends BaseListPresenter{
        void setView(MovieListContracts.View view);
        void selectQuote(Quote quote);
        void filterQuotes(String pattern);
    }
}
