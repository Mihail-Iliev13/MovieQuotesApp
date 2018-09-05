package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.presenters.ListAllQuotesPresenter;

import java.util.List;

public interface ListAllQuotesContracts {
    interface View{
        void setPresenter(Presenter presenter);
            void showQuotes(List<Quote> quotes);

    }
    interface Presenter{
        void setView(View view);
        void loadQuotes();
        void filterQuotes(String pattern);
        void selectQuotes(Quote quote);
        void presentQuotesToView(List<Quote> quotes);
    }
}
