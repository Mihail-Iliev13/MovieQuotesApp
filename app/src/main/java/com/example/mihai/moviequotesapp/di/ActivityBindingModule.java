package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.di.fragmentmodules.CreateQuoteModule;
import com.example.mihai.moviequotesapp.di.fragmentmodules.ListQuotesModule;
import com.example.mihai.moviequotesapp.di.fragmentmodules.MovieListModule;
import com.example.mihai.moviequotesapp.di.fragmentmodules.QuoteDetailsModule;
import com.example.mihai.moviequotesapp.views.activities.CreateQuoteActivity;
import com.example.mihai.moviequotesapp.views.activities.ListAllQuotesActivity;
import com.example.mihai.moviequotesapp.views.activities.MovieListActivity;
import com.example.mihai.moviequotesapp.views.activities.QuoteDetailsActivity;
import com.example.mihai.moviequotesapp.views.activities.UpdateQuoteActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped()
    @ContributesAndroidInjector(modules = CreateQuoteModule.class)
    abstract CreateQuoteActivity createQuoteActivity();

    @ActivityScoped()
    @ContributesAndroidInjector(modules = CreateQuoteModule.class)
    abstract UpdateQuoteActivity updateQuoteActivity();

    @ActivityScoped()
    @ContributesAndroidInjector(modules = ListQuotesModule.class)
    abstract ListAllQuotesActivity listQuoteActivity();

    @ActivityScoped()
    @ContributesAndroidInjector(modules = QuoteDetailsModule.class)
    abstract QuoteDetailsActivity quoteDetailsActivity();

    @ActivityScoped()
    @ContributesAndroidInjector(modules = MovieListModule.class)
    abstract MovieListActivity movieListActivity();

}