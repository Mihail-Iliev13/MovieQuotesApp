package com.example.mihai.moviequotesapp.di;

import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.repositories.base.Repository;
import com.example.mihai.moviequotesapp.services.HttpService;
import com.example.mihai.moviequotesapp.services.base.QuoteService;
import com.example.mihai.moviequotesapp.validators.base.Validator;

import dagger.Module;
import dagger.Provides;

@Module
public class QuoteServiceModule {
@Provides
public QuoteService quoteService(Repository repository, Validator validator){
    return new HttpService(repository, validator);
}

}
