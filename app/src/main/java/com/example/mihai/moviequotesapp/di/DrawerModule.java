package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.ListQuotesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DrawerModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract DrawerFragment drawerFragmentFragment();
}
