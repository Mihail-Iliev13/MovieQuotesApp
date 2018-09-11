package com.example.mihai.moviequotesapp.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.views.customadapters.ExpandableListAdapter;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.activities.QuoteDetailsActivity;
import com.example.mihai.moviequotesapp.views.contracts.MovieListContracts;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListFragment extends Fragment implements MovieListContracts.View {

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @BindView(R.id.el_movie_list)
    ExpandableListView mMovieList;

    @Inject
    ExpandableListAdapter mExListAdapter;

    private MovieListContracts.Presenter mPresenter;

    @Inject
    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);

        mMovieList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {

                Quote selectedQuote = (Quote) mExListAdapter.getChild(groupPosition, childPosition);
                mPresenter.selectQuote(selectedQuote);
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadMovies();
    }

    @Override
    public void setPresenter(MovieListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMovieList(List<String> movies, Map<String, List<Quote>> quotes) {
           getActivity().runOnUiThread(() -> {
               mExListAdapter.setListDataHeader(movies);
               mExListAdapter.setListHashMap(quotes);
               mMovieList.setAdapter(mExListAdapter);
           });
    }

    @Override
    public void showQuoteDetails(Quote quote) {
        Intent intent = new Intent(getContext(), QuoteDetailsActivity.class);
        intent.putExtra(Constants.SELECTED_QUOTE, quote);
        startActivity(intent);
    }

    @Override
    public void showEmptyMovieList() {
        getActivity().runOnUiThread(() -> {
            Toast.makeText(
                    getContext(),
                    "Movie list is empty",
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showLoading() {
        getActivity().runOnUiThread(() -> {
            mMovieList.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        getActivity().runOnUiThread(() -> {
            mMovieList.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public void showError(Exception e) {
        getActivity().runOnUiThread( () -> {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        });
    }
}
