package com.example.mihai.moviequotesapp.di.fragmentmodules;

import com.example.mihai.moviequotesapp.di.FragmentScoped;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.QuoteDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class QuoteDetailsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract QuoteDetailsFragment quoteDetailsFragment();
}