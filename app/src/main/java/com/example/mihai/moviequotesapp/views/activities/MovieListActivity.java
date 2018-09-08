package com.example.mihai.moviequotesapp.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.contracts.MovieListContracts;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.MovieListFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MovieListActivity extends DaggerAppCompatActivity {

    @Inject
    DrawerFragment mDrawer;

    @Inject
    MovieListFragment mMovieList;

    @Inject
    MovieListContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragment_layout);
        mDrawer.setID(Constants.MOVIES_LIST_ACTIVITY_ID);
        mPresenter.setView(mMovieList);
        mMovieList.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mMovieList)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer, mDrawer)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }
}
