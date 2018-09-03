package com.example.mihai.moviequotesapp.httprequester.base;

import java.io.IOException;

public interface HttpRequester {

    String get(String url) throws IOException;

    String post(String url, String json) throws IOException;

    String put(String url, String json) throws IOException;

    String delete(String url, String json) throws IOException;
}
