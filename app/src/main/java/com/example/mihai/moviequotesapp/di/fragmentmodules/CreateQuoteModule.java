package com.example.mihai.moviequotesapp.di.fragmentmodules;

import com.example.mihai.moviequotesapp.di.FragmentScoped;
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
