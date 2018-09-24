package com.example.mihai.moviequotesapp.views.activities;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.contracts.QuotesListContracts;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.ListQuotesFragment;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteDialogFragment;
import com.example.mihai.moviequotesapp.views.presenters.UpdateDeleteDialogPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class QuotesListActivity extends DaggerAppCompatActivity {

    @Inject
    DrawerFragment mDrawer;

    @Inject
    ListQuotesFragment mQuotesListFragment;

    @Inject
    UpdateDeleteDialogFragment mDialog;

    @Inject
    QuotesListContracts.Presenter mListPresenter;

    @Inject
    UpdateDeleteDialogPresenter mDialogPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragment_layout);

        mDrawer.setID(Constants.QUOTES_LIST_ACTIVITY_ID);
        setSupportActionBar(mDrawer.getToolbar());

        mQuotesListFragment.setPresenter(mListPresenter);
        mDialog.setPresenter(mDialogPresenter);
        mDialog.setListPresenter(mListPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mQuotesListFragment)
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
        searchView.setOnQueryTextListener(mQuotesListFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListPresenter.setView(mQuotesListFragment);
        mDialogPresenter.setView(mDialog);
        mQuotesListFragment.setDialog(mDialog);
    }
}
