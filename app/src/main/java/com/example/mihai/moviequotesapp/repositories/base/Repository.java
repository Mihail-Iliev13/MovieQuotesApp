package com.example.mihai.moviequotesapp.repositories.base;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {

    List<T> getAll() throws IOException;

    void add(T object) throws IOException;

    void update(int id, T object) throws IOException;

    void delete (int id, T object) throws IOException;

}
