package com.example.mihai.moviequotesapp.views.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.mihai.moviequotesapp.R;

import dagger.android.support.DaggerAppCompatActivity;

public class ListAllQuotesActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_quotes);
    }
}
