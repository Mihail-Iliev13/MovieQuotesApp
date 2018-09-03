package com.example.mihai.moviequotesapp.parsers.base;

import java.util.List;

public interface JsonParser<T> {

    List<T> fromJsonToList(String json);
    T fromJsonToObject (String json);
    String toJson (T object);

}
