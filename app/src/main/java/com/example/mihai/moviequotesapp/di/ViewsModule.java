package com.example.mihai.moviequotesapp.di;


import android.content.Context;

import com.example.mihai.moviequotesapp.views.customadapters.ExpandableListAdapter;
import com.example.mihai.moviequotesapp.views.customadapters.QuotesAdapter;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {
@Provides
 public QuotesAdapter adapter(Context context){
    return new QuotesAdapter(context, R.layout.custom_list_view);
}

@Provides
public ExpandableListAdapter expandableListAdapter(Context context, List<String> list,
                                                   HashMap<String, List<Quote>> map) {
    return new ExpandableListAdapter(context, list, map);
}

@Provides
public List<String> list(){
    return new ArrayList<>();
}

@Provides
public HashMap<String, List<Quote>> map(){
    return new HashMap<>();
}

}
