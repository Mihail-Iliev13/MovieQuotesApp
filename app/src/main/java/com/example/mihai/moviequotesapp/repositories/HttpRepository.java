package com.example.mihai.moviequotesapp.repositories;

import com.example.mihai.moviequotesapp.httprequesters.base.HttpRequester;
import com.example.mihai.moviequotesapp.parsers.base.JsonParser;
import com.example.mihai.moviequotesapp.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

public class HttpRepository<T> implements Repository<T> {

    private String mServerUrl;
    private HttpRequester mRequester;
    private JsonParser<T> mJsonParser;

    public HttpRepository (String url, HttpRequester requester, JsonParser jsonParser) {
        this.mServerUrl = url;
        this.mRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public List<T> getAll() throws IOException {
        String url = mServerUrl + "/all";
        String response = mRequester.get(url);
        return mJsonParser.fromJsonToList(response);
    }

    @Override
    public void add(T object) throws IOException {
        String url = mServerUrl + "/new";
        String json = mJsonParser.toJson(object);
        mRequester.post(url, json);
    }

    @Override
    public void update(int id, T object) throws IOException {
        String url = mServerUrl + "/id/" + id;
        String json = mJsonParser.toJson(object);
        mRequester.put(url, json);
    }

    @Override
    public void delete(int id, T object) throws IOException {
        String url = mServerUrl + "/delete/" + id;
        String json = mJsonParser.toJson(object);
        mRequester.delete(url, json);
    }
}
