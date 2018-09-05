package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.views.fragments.ListQuotesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ListQuotesModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ListQuotesFragment listQuotesFragment();
}
