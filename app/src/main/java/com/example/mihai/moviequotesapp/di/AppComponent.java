package com.example.mihai.moviequotesapp.di;

import android.app.Application;

import com.example.mihai.moviequotesapp.MovieQuotesApp;
import com.example.mihai.moviequotesapp.views.presenters.ListAllQuotesPresenter;
import com.example.mihai.moviequotesapp.views.presenters.UpdateQuotePresenter;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        RepositoriesModule.class,
        ParsersModule.class,
        HttpRequesterModule.class,
        QuoteServiceModule.class,
        AsyncRunnerModule.class,
        GsonModule.class,
        UpdatePresenterModule.class,
        CreatePresenterModule.class,
        ValidatorModule.class,
        ListPresenterModule.class,
        ViewsModule.class,
})
public interface AppComponent extends AndroidInjector<MovieQuotesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
