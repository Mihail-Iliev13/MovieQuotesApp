package com.example.mihai.moviequotesapp;

import com.example.mihai.moviequotesapp.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MovieQuotesApp extends DaggerApplication{
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        Point point = Point.create(1, 2);
        System.out.println(point.hashCode());
    return DaggerAppComponent.builder().application(this).build();

    }
}
