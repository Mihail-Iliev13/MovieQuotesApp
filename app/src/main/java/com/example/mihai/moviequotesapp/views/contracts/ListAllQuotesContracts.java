package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.fragments.GenerateQuoteFragment;
import com.example.mihai.moviequotesapp.views.presenters.ListAllQuotesPresenter;

import java.io.IOException;
import java.util.List;

public interface ListAllQuotesContracts {
    interface View {
        void setPresenter(Presenter presenter);
        void showQuotes(List<Quote> quotes);
        void showEmptyQuotesList();
        void showDialogBox();
        void showUpdateActivity();
        void showToast();
        void showQuoteDetails(Quote quote);
        void showError(Exception e);
        void showLoading();
        void hideLoading();
    }
    interface Presenter{
        void setView(View view);
        void loadQuotes();
        void filterQuotes(String pattern);
        void selectQuote(Quote quote);
        void selectOnLong(Quote quote);
        void deleteQuote(Quote quote);
        void navigateToUpdate();
    }
}
