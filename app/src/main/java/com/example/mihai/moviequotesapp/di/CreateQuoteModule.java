package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.views.fragments.GenerateQuoteFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class CreateQuoteModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract GenerateQuoteFragment createQuoteFragment();
}
