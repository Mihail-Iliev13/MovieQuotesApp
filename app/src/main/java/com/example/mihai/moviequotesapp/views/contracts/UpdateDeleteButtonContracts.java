package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;

public interface UpdateDeleteButtonContracts {

    interface View {
       void setPresenter(Presenter presenter);
       void showUpdateActivity();
        void setSelectedQuote(Quote quote);
    }

    interface Presenter {
        void update();
        void delete(Quote quote);
        void setView(View view);
        void navigateToUpdate();
    }

}
