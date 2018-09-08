package com.example.mihai.moviequotesapp.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.activities.CreateQuoteActivity;
import com.example.mihai.moviequotesapp.views.activities.ListAllQuotesActivity;
import com.example.mihai.moviequotesapp.views.activities.MovieListActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import javax.inject.Inject;

public class DrawerFragment extends Fragment {

    private android.support.v7.widget.Toolbar mToolbar;

    private long mCurrentActivityID;

    @Inject
    public DrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_drawer, container, false);
       mToolbar = view.findViewById(R.id.tb_menu);
       return view;
    }

    public void setupDrawer() {

        PrimaryDrawerItem createQuote = new PrimaryDrawerItem()
                .withIdentifier(Constants.CREATE_ACTIVITY_ID)
                .withIcon(R.drawable.ic_create)
                .withName("Create quote");

        PrimaryDrawerItem listQuotes = new PrimaryDrawerItem()
                .withIdentifier(Constants.QUOTES_LIST_ACTIVITY_ID)
                .withIcon(R.drawable.ic_format_quote)
                .withName("All Quotes");

        PrimaryDrawerItem moviesList = new PrimaryDrawerItem()
                .withIdentifier(Constants.MOVIES_LIST_ACTIVITY_ID)
                .withIcon(R.drawable.ic_local_movies)
                .withName("All Movies");

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(getActivity())
                .withHeaderBackground(R.color.primary)
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(getActivity())
                .withToolbar(mToolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        createQuote,
                        new DividerDrawerItem(),
                        listQuotes,
                        new DividerDrawerItem(),
                        moviesList
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long identifier = drawerItem.getIdentifier();

                    if (mCurrentActivityID == identifier) {
                        return false;
                    }

                    Intent intent = getNextIntent(identifier);

                    if (intent == null) {
                        return false;
                    }

                    startActivity(intent);
                    return true;
                })
                .build();
    }

    private Intent getNextIntent(long identifier) {

        if (identifier == Constants.QUOTES_LIST_ACTIVITY_ID) {
            return new Intent(getContext(), ListAllQuotesActivity.class);

        } else if (identifier == Constants.CREATE_ACTIVITY_ID){

            return new Intent(getContext(), CreateQuoteActivity.class);

        }  else if (identifier == Constants.MOVIES_LIST_ACTIVITY_ID) {
            return new Intent(getContext(), MovieListActivity.class);
        } else {
            return null;
        }
    }
    public void setID(long ID){
        mCurrentActivityID = ID;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
