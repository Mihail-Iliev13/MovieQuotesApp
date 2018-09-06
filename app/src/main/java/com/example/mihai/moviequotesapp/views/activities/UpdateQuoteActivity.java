package com.example.mihai.moviequotesapp.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.GenerateQuoteFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class UpdateQuoteActivity extends DaggerAppCompatActivity {

    @Inject
    public DrawerFragment mDrawer;

    @Inject
    public GenerateQuoteFragment mGenerateQuoteFragment;

    @Inject
    public GenerateQuoteContracts.UpdatePresenter mPresenter;

    private Quote mCLickedQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragment_layout);

        setSupportActionBar(mDrawer.getToolbar());

        mPresenter.setView(mGenerateQuoteFragment);
        mGenerateQuoteFragment.setPresenter(mPresenter);

        Intent intent = getIntent();
        mCLickedQuote = (Quote) intent.getSerializableExtra(Constants.SELECTED_QUOTE);
        mPresenter.setClickedQuote(mCLickedQuote);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer, mDrawer)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mGenerateQuoteFragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
        mPresenter.fillFields();
    }
}
