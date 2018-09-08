package com.example.mihai.moviequotesapp.parsers.base;

import com.example.mihai.moviequotesapp.models.Quote;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public interface JsonParser {

    List<Quote> fromJsonToList(String json);
    Quote fromJsonToObject (String json);
    String toJson (Quote object);
    HashMap<String, List<Quote>> fromJsonToMap(String json, Type typeOfHashMap);

}
