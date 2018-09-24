package com.example.mihai.moviequotesapp.views.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.contracts.MovieListContracts;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.MovieListFragment;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteDialogFragment;
import com.example.mihai.moviequotesapp.views.presenters.UpdateDeleteDialogPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MovieListActivity extends DaggerAppCompatActivity {

    @Inject
    DrawerFragment mDrawer;

    @Inject
    MovieListFragment mMovieList;

    @Inject
    UpdateDeleteDialogFragment mDialog;

    @Inject
    MovieListContracts.Presenter mPresenter;

    @Inject
    UpdateDeleteDialogPresenter mDialogPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragment_layout);

        mDrawer.setID(Constants.MOVIES_LIST_ACTIVITY_ID);
        setSupportActionBar(mDrawer.getToolbar());

        mMovieList.setPresenter(mPresenter);
        mDialog.setPresenter(mDialogPresenter);
        mDialog.setListPresenter(mPresenter);

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

        mDrawer.getToolbar()
                .inflateMenu(R.menu.toolbar_menu);

        MenuItem item = mDrawer
                .getToolbar()
                .getMenu()
                .findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(mMovieList);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(mMovieList);
        mDialogPresenter.setView(mDialog);
        mMovieList.setDialog(mDialog);
    }

}
