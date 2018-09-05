package com.example.mihai.moviequotesapp.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.fragments.GenerateQuoteFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class UpdateQuoteActivity extends DaggerAppCompatActivity {

    @Inject
    public GenerateQuoteFragment mGenerateQuoteFragment;

    @Inject
    public GenerateQuoteContracts.UpdatePresenter mPresenter;

    private Quote mCLickedQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_quote);

        mPresenter.setView(mGenerateQuoteFragment);
        mGenerateQuoteFragment.setPresenter(mPresenter);

        Intent intent = getIntent();
        mCLickedQuote = (Quote) intent.getSerializableExtra("dfsa");
        mPresenter.setClickedQuote(mCLickedQuote);
        mPresenter.fillFields();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mGenerateQuoteFragment)
                .commit();
    }
}
