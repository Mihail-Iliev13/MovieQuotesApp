package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.fragments.QuoteDetailsFragment;

public interface QuoteDetailsContracts {

    interface View {
        void showQuote(Quote quote);
        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void setView(View view);
        void setSelectedQuoteID(int id);
        void loadQuote();
    }
}
