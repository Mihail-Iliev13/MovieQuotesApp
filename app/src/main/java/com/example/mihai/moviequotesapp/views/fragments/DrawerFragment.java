package com.example.mihai.moviequotesapp.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.activities.CreateQuoteActivity;
import com.example.mihai.moviequotesapp.views.activities.ListAllQuotesActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import javax.inject.Inject;

import butterknife.BindView;

public class DrawerFragment extends Fragment {

    @BindView(R.id.tb_menu)
    public android.support.v7.widget.Toolbar mToolbar;

    public static final long LIST_QUOTES_ID = 2;
    public static final long CREATE_QUOTE_ID = 3;
    private long mCurrentActivityID;

    @Inject
    public DrawerFragment() {
        // Required empty public constructor
    }

    public static DrawerFragment newInstance(){
        return new DrawerFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_drawer, container, false);

    }

    public void setupDrawer() {

        PrimaryDrawerItem createQuote = new PrimaryDrawerItem()
                .withIdentifier(CREATE_QUOTE_ID)
                .withIcon(R.drawable.ic_create)
                .withName("Create quote");

        PrimaryDrawerItem listQuotes = new PrimaryDrawerItem()
                .withIdentifier(LIST_QUOTES_ID)
                .withIcon(R.drawable.ic_format_quote)
                .withName("All Quotes");

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(getActivity())
                .withHeaderBackground(R.color.md_blue_300)
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(getActivity())
                .withToolbar(mToolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        createQuote,
                        new DividerDrawerItem(),
                        listQuotes
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

        if (identifier == LIST_QUOTES_ID) {
            return new Intent(getContext(), ListAllQuotesActivity.class);

        } else if (identifier == CREATE_QUOTE_ID){

            return new Intent(getContext(), CreateQuoteActivity.class);

        }  else {
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
