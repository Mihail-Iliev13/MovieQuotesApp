package com.example.mihai.moviequotesapp.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.views.fragments.QuoteDetailsFragment;
import com.example.mihai.moviequotesapp.views.presenters.QuoteDetailsPresenter;

import dagger.android.support.DaggerAppCompatActivity;

public class QuoteDetailsActivity extends DaggerAppCompatActivity{
    public static final String EXTRA_KEY = "QUOTE_EXTRA_KEY";
    private QuoteDetailsFragment mQuoteDetailsFragment;
    private QuoteDetailsPresenter mQuoteDetailsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);
        QuoteService superheroesService = null;
//        superheroesService = AndroidApplication.getSuperheroesService();
//
//        mSuperheroDetailsPresenter =
//                new SuperheroDetailsPresenter(superheroesService);

        Intent intent = getIntent();
        Quote quote= (Quote) intent.getSerializableExtra(QuoteDetailsActivity.EXTRA_KEY);
        mQuoteDetailsPresenter.setQuoteId(quote.getId());

        mQuoteDetailsFragment = QuoteDetailsFragment.newInstance();
        mQuoteDetailsFragment.setPresenter(mQuoteDetailsPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mQuoteDetailsFragment)
                .commit();
    }

}
