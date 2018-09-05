package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.views.activities.CreateQuoteActivity;
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


}