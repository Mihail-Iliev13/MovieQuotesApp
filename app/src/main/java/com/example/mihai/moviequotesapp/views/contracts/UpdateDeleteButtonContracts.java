package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;

public interface UpdateDeleteButtonContracts {

    interface View {
       void setPresenter(Presenter presenter);
       void showUpdateActivity(Quote quote);
       void endActivity();
    }

    interface Presenter {
        void update();
        void delete();
        void setView(View view);
        void setSelectedQuoteID(int id);
    }
}
