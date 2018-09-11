package com.example.mihai.moviequotesapp.repositories.base;

import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public interface Repository {

    List<Quote> getAll() throws IOException;

    void add(Quote object) throws IOException;

    void update(int id, Quote object) throws IOException;

    void delete (int id, Quote object) throws IOException;

}
