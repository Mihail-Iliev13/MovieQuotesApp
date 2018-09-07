package com.example.mihai.moviequotesapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.QuoteDetailsFragment;
import com.example.mihai.moviequotesapp.views.presenters.QuoteDetailsPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class QuoteDetailsActivity extends DaggerAppCompatActivity{

    @Inject
    public DrawerFragment mDrawer;

    @Inject
    public QuoteDetailsFragment mQuoteDetailsFragment;

    @Inject
    public QuoteDetailsPresenter mQuoteDetailsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragment_layout);


        Intent intent = getIntent();
        Quote quote = (Quote) intent.getSerializableExtra(Constants.SELECTED_QUOTE);

        mQuoteDetailsPresenter.setSelectedQuote(quote);
        mQuoteDetailsPresenter.setView(mQuoteDetailsFragment);
        mQuoteDetailsFragment.setPresenter(mQuoteDetailsPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer, mDrawer)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mQuoteDetailsFragment)
                .commit();
    }

}
