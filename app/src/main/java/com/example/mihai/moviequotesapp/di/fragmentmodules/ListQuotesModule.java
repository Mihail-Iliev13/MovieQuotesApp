package com.example.mihai.moviequotesapp.di.fragmentmodules;

import com.example.mihai.moviequotesapp.di.FragmentScoped;
import com.example.mihai.moviequotesapp.views.fragments.ListQuotesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ListQuotesModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ListQuotesFragment listQuotesFragment();
}
