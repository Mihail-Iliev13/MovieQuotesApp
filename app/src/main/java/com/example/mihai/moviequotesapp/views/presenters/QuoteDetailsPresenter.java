package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.activities.QuoteDetailsActivity;
import com.example.mihai.moviequotesapp.views.contracts.QuoteDetailsContracts;

import java.io.IOException;

public class QuoteDetailsPresenter implements QuoteDetailsContracts.Presenter{
    private  QuoteService mQuotesService;
    private  AsyncRunner mAsyncRunner;
    private QuoteDetailsContracts.View mView;
    private int mQuoteId;

    public QuoteDetailsPresenter(
            QuoteService quotesService,
            AsyncRunner asyncRunner
    ) {
        mQuotesService = quotesService;
        mAsyncRunner = asyncRunner;
    }


    @Override
    public void subscribe(QuoteDetailsContracts.View view) {
mView=view;
    }

    @Override
    public void loadQuote() {
        mAsyncRunner.runInBackground(() -> {
            try {Quote quote  = mQuotesService.getDetailsById(mQuoteId);
                mView.showQuote(quote);
            } catch (IOException e) {
                e.printStackTrace();

            }

        });

    }
    public void setQuoteId(int quoteId) {
        mQuoteId = quoteId;
    }

}
