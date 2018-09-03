package com.example.mihai.moviequotesapp.parsers.base;

public interface JsonParser<T> {

    T[] fromJsonToArray(String json);
    T fromJsonToObject (String json);
    String toJson (T object);

}
