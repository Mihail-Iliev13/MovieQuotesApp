package com.example.mihai.moviequotesapp.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.fragments.GenerateQuoteFragment;
import com.example.mihai.moviequotesapp.views.presenters.CreateQuotePresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class CreateQuoteActivity extends DaggerAppCompatActivity {

    @Inject
    public GenerateQuoteFragment mGenerateQuoteFragment;

    @Inject
    public GenerateQuoteContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quote);

//        mGenerateQuoteFragment = GenerateQuoteFragment.newInstance();
//        mPresenter = new CreateQuotePresenter();
        mPresenter.setView(mGenerateQuoteFragment);
        mGenerateQuoteFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mGenerateQuoteFragment)
                .commit();
    }
}
