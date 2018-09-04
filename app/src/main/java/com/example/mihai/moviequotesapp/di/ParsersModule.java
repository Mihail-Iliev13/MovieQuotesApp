package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.parsers.GsonParser;
import com.example.mihai.moviequotesapp.parsers.base.JsonParser;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {

@Provides
    public JsonParser<Quote> jsonParser (Class<Quote> klaas, Class<Quote[]> arrayKlaas, Gson gson) {
    return new GsonParser<>(klaas, arrayKlaas, gson);
}
}
