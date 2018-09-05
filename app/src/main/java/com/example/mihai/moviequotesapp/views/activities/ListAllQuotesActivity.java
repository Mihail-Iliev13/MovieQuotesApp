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

}
