package com.example.mihai.moviequotesapp.parsers;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.parsers.base.JsonParser;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class GsonParser implements JsonParser {


    private Gson mGson;

    @Inject
    public GsonParser(Gson gson){
        this.mGson = gson;
    }

    @Override
    public List<Quote> fromJsonToList(String json) {
       Quote[] array = mGson.fromJson(json, Quote[].class);
        return Arrays.asList(array);
    }

    @Override
    public Quote fromJsonToObject(String json) {
        return mGson.fromJson(json, Quote.class);
    }

    @Override
    public String toJson(Quote object) {
        return mGson.toJson(object);
    }
}
