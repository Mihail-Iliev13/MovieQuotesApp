package com.example.mihai.moviequotesapp.di;


import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {
@Provides
public Gson gson () {
    return new Gson();
}

}
