package com.example.mihai.moviequotesapp.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.SearchView;

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

public class MovieListFragment extends Fragment implements MovieListContracts.View,
        SearchView.OnQueryTextListener {

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @BindView(R.id.el_movie_list)
    ExpandableListView mMovieList;

    @Inject
    ExpandableListAdapter mExListAdapter;

    private MovieListContracts.Presenter mPresenter;

    private UpdateDeleteDialogFragment mDialogFragment;


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

        mMovieList.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

            Quote selectedQuote = (Quote) mExListAdapter
                    .getChild(groupPosition, childPosition);
            mPresenter.selectQuote(selectedQuote);
            return true;
        });

        mMovieList.setOnItemLongClickListener((parent, view1, position, id) -> {

            if (ExpandableListView.getPackedPositionType(id)
                    == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

                int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                int childPosition = ExpandableListView.getPackedPositionChild(id);
                Quote selectedQuote = (Quote) mExListAdapter.getChild(groupPosition, childPosition);
                showDialogBox(selectedQuote);

                return true;
            }

            return false;
        });
        return view;
    }

    @Override
    public void showDialogBox(Quote quote) {

        FragmentManager manager = getFragmentManager();
        mDialogFragment.getDialogPresenter()
                .setSelectedQuoteID(quote.getId());
        mDialogFragment.show(manager, "Alert Dialog");
    }

    @Override
    public void setDialog(UpdateDeleteDialogFragment dialogFragment) {
        this.mDialogFragment = dialogFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadItems();
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String pattern = newText.toLowerCase();
        mPresenter.filterQuotes(pattern);
        return true;
    }
}
