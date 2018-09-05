package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.httprequesters.OkHttpRequester;
import com.example.mihai.moviequotesapp.httprequesters.base.HttpRequester;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class HttpRequesterModule {
@Provides
public HttpRequester httpRequester(){
    return new OkHttpRequester();
}

@Provides
@Named("baseServerUrl")
public String baseServerUrl() {
    return Constants.BASE_SERVER_URL;
}
}
