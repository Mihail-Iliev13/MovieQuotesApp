package com.example.mihai.moviequotesapp.validators.base;

import com.example.mihai.moviequotesapp.models.Quote;

public interface Validator {

    boolean isValid(Quote quote) throws IllegalArgumentException;
}
