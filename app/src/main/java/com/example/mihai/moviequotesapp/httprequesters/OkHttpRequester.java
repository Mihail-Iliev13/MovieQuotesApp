package com.example.mihai.moviequotesapp.httprequesters;

import com.example.mihai.moviequotesapp.httprequesters.base.HttpRequester;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequester implements HttpRequester {

    @Inject
    public OkHttpRequester () {

    }

    @Override
    public String get(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String post(String url, String json) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response =
                client.newCall(request)
                .execute();

        int b = 6;
        return response.body().string();
    }

    @Override
    public String put(String url, String json) throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), json);

        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();

        Response response = client.newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String delete(String url, String json) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .delete(requestBody)
                .build();

        Response response = client.newCall(request)
                .execute();

        return response.body().string();
    }
}
