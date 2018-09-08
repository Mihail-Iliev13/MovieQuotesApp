package com.example.mihai.moviequotesapp.repositories;

import com.example.mihai.moviequotesapp.httprequesters.base.HttpRequester;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.parsers.base.JsonParser;
import com.example.mihai.moviequotesapp.repositories.base.Repository;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class HttpRepository implements Repository {

    private String mServerUrl;
    private HttpRequester mRequester;
    private JsonParser mJsonParser;

    @Inject
    public HttpRepository (String url, HttpRequester requester,
                           JsonParser jsonParser) {

        this.mServerUrl = url;
        this.mRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public List<Quote> getAll() throws IOException {
        String url = mServerUrl + "/all";
        String response = mRequester.get(url);
        return mJsonParser.fromJsonToList(response);
    }

    @Override
    public void add(Quote object) throws IOException {
        String url = mServerUrl + "/new";
        String json = mJsonParser.toJson(object);
        mRequester.post(url, json);
    }

    @Override
    public void update(int id, Quote object) throws IOException {
        String url = mServerUrl + "/update/" + id;
        String json = mJsonParser.toJson(object);
        mRequester.put(url, json);
    }

    @Override
    public void delete(int id, Quote object) throws IOException {
        String url = mServerUrl + "/delete/" + id;
        String json = mJsonParser.toJson(object);
        mRequester.delete(url, json);
    }

    @Override
    public HashMap<String, List<Quote>> getAllMovies() throws IOException {
        String url = mServerUrl + "/movies";
        String response = mRequester.get(url);
        Type typeOfHashMap = new TypeToken<HashMap<String, List<Quote>>>() { }.getType();
        return mJsonParser.fromJsonToMap(response, typeOfHashMap);
    }

}
