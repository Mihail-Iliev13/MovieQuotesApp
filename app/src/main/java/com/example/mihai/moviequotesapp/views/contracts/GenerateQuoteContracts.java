package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;

public interface GenerateQuoteContracts {

    interface View {

        void setPresenter(Presenter mPresenter);
        void showToast();
        void setQuoteBody(String quoteBody);
        void setQuoteMovie(String movie);
        void setQuotedCharacter(String quotedCharacter);
        void setRating(float rating);
        void changeButton();
        void showError(IllegalArgumentException e);
    }

    interface Presenter {

        void generateQuote(String quoteText, String quoteMovie, String quotedCharacter,
                           float quoteRating) throws IOException;

        void setView(View view);
    }

    interface UpdatePresenter extends Presenter {
        void fillFields();
        void setSelectedQuote(Quote quote);
    }

}
