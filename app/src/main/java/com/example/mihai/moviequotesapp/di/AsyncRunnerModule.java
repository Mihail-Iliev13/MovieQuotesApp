package com.example.mihai.moviequotesapp.di;


import com.example.mihai.moviequotesapp.async.AsyncRunnerImpl;
import com.example.mihai.moviequotesapp.async.base.AsyncRunner;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncRunnerModule {
@Provides
public AsyncRunner asyncRunner () {
    return new AsyncRunnerImpl();
}
}
