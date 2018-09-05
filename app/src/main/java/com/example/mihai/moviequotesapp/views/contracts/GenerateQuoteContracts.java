package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;

public interface GenerateQuoteContracts {

    interface View {

        void setPresenter(Presenter mPresenter);
        void showToast(Quote quote);
        String getQuoteBody();
        String getQuoteMovie();
        String getQuotedCharacter();
        float getRating();

        void setQuoteBody(String quoteBody);
        void setQuoteMovie(String movie);
        void setQuotedCharacter(String quotedCharacter);
        void setRating(float rating);
        void makeButtonBlue();
    }

    interface Presenter {

        void generateQuote() throws IOException;

        void setView(View view);
    }

    interface UpdatePresenter extends Presenter {

        void fillFields();

        void setClickedQuote(Quote quote);

    }

}
