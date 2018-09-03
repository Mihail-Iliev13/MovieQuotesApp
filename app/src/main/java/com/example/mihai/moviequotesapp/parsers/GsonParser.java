package com.example.mihai.moviequotesapp.parsers;

import com.example.mihai.moviequotesapp.parsers.base.JsonParser;
import com.google.gson.Gson;

public class GsonParser<T> implements JsonParser<T> {

    private Class<T> mKlass;
    private Class<T[]> mKlassArray;
    private Gson mGson;

    public GsonParser(Class<T> klass,Class<T[]> klassArray, Gson gson){
        this.mKlass = klass;
        this.mKlassArray = klassArray;
        this.mGson = gson;
    }

    @Override
    public T[] fromJsonToArray(String json) {
       return mGson.fromJson(json, mKlassArray);
    }

    @Override
    public T fromJsonToObject(String json) {
        return mGson.fromJson(json, mKlass);
    }

    @Override
    public String toJson(T object) {
        return mGson.toJson(object);
    }
}
