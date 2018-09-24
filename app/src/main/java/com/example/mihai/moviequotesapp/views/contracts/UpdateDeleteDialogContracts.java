package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;

public interface UpdateDeleteDialogContracts {

    interface View {
        void setPresenter(Presenter presenter);
        void showUpdateActivity(Quote quote);

    }

    interface Presenter{
        void setView(View view);
        void update();
        void delete();
        void setSelectedQuoteID(int id);
    }

}
