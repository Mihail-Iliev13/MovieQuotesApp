package com.example.mihai.moviequotesapp.di;


import com.example.mihai.moviequotesapp.httprequesters.base.HttpRequester;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.parsers.base.JsonParser;
import com.example.mihai.moviequotesapp.repositories.HttpRepository;
import com.example.mihai.moviequotesapp.repositories.base.Repository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

@Provides
  public Repository quoteRepository (@Named("baseServerUrl") String baseUrl,
                                            HttpRequester requester,
                                            JsonParser jsonParser){
    return new HttpRepository(baseUrl, requester, jsonParser);
}
}
