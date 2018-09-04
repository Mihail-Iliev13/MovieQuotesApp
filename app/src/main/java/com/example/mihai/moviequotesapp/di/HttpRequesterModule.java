package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.httprequesters.OkHttpRequester;
import com.example.mihai.moviequotesapp.httprequesters.base.HttpRequester;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class HttpRequesterModule {
@Provides
public HttpRequester httpRequester(OkHttpClient client){
    return new OkHttpRequester(client);
}
}
