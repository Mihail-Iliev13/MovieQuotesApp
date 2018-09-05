package com.example.mihai.moviequotesapp.di;


import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.mihai.moviequotesapp.QuotesAdapter;
import com.example.mihai.moviequotesapp.R;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {
@Provides
 public QuotesAdapter adapter(Context context){
    return new QuotesAdapter(context, R.layout.custom_list_view);
}
}
