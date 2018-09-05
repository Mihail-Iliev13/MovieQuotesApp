package com.example.mihai.moviequotesapp.di;


import com.example.mihai.moviequotesapp.validators.QuoteValidator;
import com.example.mihai.moviequotesapp.validators.base.Validator;

import dagger.Module;
import dagger.Provides;

@Module
public class ValidatorModule {

@Provides
public Validator validator () {
    return new QuoteValidator();
}
}
