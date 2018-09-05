package com.example.mihai.moviequotesapp.views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mihai.moviequotesapp.QuotesAdapter;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.repositories.HttpRepository;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.contracts.ListAllQuotesContracts;
import com.example.mihai.moviequotesapp.views.presenters.ListAllQuotesPresenter;

import java.io.IOException;
import java.util.List;

import dagger.android.support.DaggerAppCompatActivity;

public class ListAllQuotesActivity extends DaggerAppCompatActivity implements ListAllQuotesPresenter.Presenter{
    private QuoteService mQuoteService;
    private AsyncRunner mAsyncRunner;
    private ListAllQuotesContracts.View mView;


    @Override
    public void subscribe(ListAllQuotesContracts.View view) {
        mView=view;
    }

    @Override
    public void loadQuotes() {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes = mQuoteService.getAll();
                presentQuotesToView(quotes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            });
    }


    @Override
    public void flterQuotes(String pattern) {
        mAsyncRunner.runInBackground(() -> {
            try {
                List<Quote> quotes =
                        mQuoteService.getFilteredQuotes(pattern);
                presentQuotesToView(quotes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void selectQuotes(Quote quote) {
        mView.showQuoteDetails(quote);
    }

    @Override
    public void presentQuotesToView(List<Quote> quotes) {
            mView.showQuotes(quotes);
            }
}
