package com.example.mihai.moviequotesapp.views.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.QuoteDetailsContracts;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteButtonContracts;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.QuoteDetailsFragment;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteButtonsFragment;
import com.example.mihai.moviequotesapp.views.presenters.QuoteDetailsPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class QuoteDetailsActivity extends DaggerAppCompatActivity{

    @Inject
    public DrawerFragment mDrawer;

    @Inject
    public QuoteDetailsFragment mQuoteDetailsFragment;

    @Inject
    public UpdateDeleteButtonsFragment mUpdateDeleteButtonsFragment;

    @Inject
    public QuoteDetailsContracts.Presenter mQuoteDetailsPresenter;

    @Inject
    public UpdateDeleteButtonContracts.Presenter mUpdateDeletePresenter;

    private Quote mSelectedQuote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);


        Intent intent = getIntent();
        mSelectedQuote = (Quote) intent.getSerializableExtra(Constants.SELECTED_QUOTE);

        mQuoteDetailsFragment.setPresenter(mQuoteDetailsPresenter);
        mUpdateDeleteButtonsFragment.setPresenter(mUpdateDeletePresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer, mDrawer)
                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mQuoteDetailsFragment)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.buttons, mUpdateDeleteButtonsFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mQuoteDetailsPresenter.setView(mQuoteDetailsFragment);
        mQuoteDetailsPresenter.setSelectedQuoteID(mSelectedQuote.getId());
        mUpdateDeletePresenter.setView(mUpdateDeleteButtonsFragment);
        mUpdateDeletePresenter.setSelectedQuoteID(mSelectedQuote.getId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }

}
