package com.example.mihai.moviequotesapp.di.fragmentmodules;

import com.example.mihai.moviequotesapp.di.FragmentScoped;
import com.example.mihai.moviequotesapp.views.fragments.ListQuotesFragment;
import com.example.mihai.moviequotesapp.views.fragments.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MovieListFragment movieListFragment();
}
