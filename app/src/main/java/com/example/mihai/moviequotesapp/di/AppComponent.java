package com.example.mihai.moviequotesapp.di;

import android.app.Application;

import com.example.mihai.moviequotesapp.MovieQuotesApp;
import com.example.mihai.moviequotesapp.di.presentermodules.CreatePresenterModule;
import com.example.mihai.moviequotesapp.di.presentermodules.ListPresenterModule;
import com.example.mihai.moviequotesapp.di.presentermodules.QuoteDetailsPresenterModule;
import com.example.mihai.moviequotesapp.di.presentermodules.UpdatePresenterModule;

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
        QuoteDetailsPresenterModule.class
})
public interface AppComponent extends AndroidInjector<MovieQuotesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
