package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.baseListContracts.BaseListPresenter;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteDialogFragment;

import java.util.List;

public interface QuotesListContracts {
    interface View {
        void setPresenter(Presenter presenter);
        void showQuotes(List<Quote> quotes);
        void showEmptyQuotesList();
        void showDialogBox(Quote quote);
        void showQuoteDetails(Quote quote);
        void showError(Throwable e);
        void setDialog(UpdateDeleteDialogFragment dialog);
        void showLoading();
        void hideLoading();
    }
    interface Presenter extends BaseListPresenter{
        void setView(View view);
        void filterQuotes(String pattern);
        void selectQuote(Quote quote);
        void selectOnLong(Quote quote);
    }
}
