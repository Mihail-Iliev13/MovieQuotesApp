package com.example.mihai.moviequotesapp.views.presenters;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.QuoteDetailsContracts;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class QuoteDetailsPresenter implements QuoteDetailsContracts.Presenter{

    private  QuoteService mQuotesService;
    private  AsyncRunner mAsyncRunner;
    private QuoteDetailsContracts.View mView;
    private int mQuoteID;

    @Inject
    public QuoteDetailsPresenter( QuoteService quotesService, AsyncRunner asyncRunner) {
        mQuotesService = quotesService;
        mAsyncRunner = asyncRunner;
    }

    @Override
    public void setView(QuoteDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void setSelectedQuoteID(int id) {
        mQuoteID = id;
    }

    @Override
    public void loadQuote() {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quoteList = mQuotesService.getAll();
                for (Quote quote : quoteList) {
                    if (quote.getId() == mQuoteID) {
                        mView.showQuote(quote);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
