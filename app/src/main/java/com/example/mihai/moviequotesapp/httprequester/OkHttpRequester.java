package com.example.mihai.moviequotesapp.httprequester;

import com.example.mihai.moviequotesapp.httprequester.base.HttpRequester;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequester implements HttpRequester {

    private OkHttpClient mClient;

    public OkHttpRequester (OkHttpClient client) {
        this.mClient = client;
    }

    @Override
    public String get(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = mClient.newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String post(String url, String json) throws IOException {

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = mClient.newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String put(String url, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), json);

        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();

        Response response = mClient.newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String delete(String url, String json) throws IOException {
        
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .delete(requestBody)
                .build();

        Response response = mClient.newCall(request)
                .execute();

        return response.body().string();
    }
}
