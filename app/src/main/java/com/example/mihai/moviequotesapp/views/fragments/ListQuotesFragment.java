package com.example.mihai.moviequotesapp.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.views.contracts.QuotesListContracts;
import com.example.mihai.moviequotesapp.views.customadapters.QuotesAdapter;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.activities.QuoteDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import io.reactivex.Observable;


public class ListQuotesFragment extends Fragment implements QuotesListContracts.View, SearchView.OnQueryTextListener {

    @BindView(R.id.lv_quotes)
    ListView mQuotesList;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @Inject
    QuotesAdapter mAdapter;

    private QuotesListContracts.Presenter mPresenter;


    private UpdateDeleteDialogFragment mDialogFragment;


    @Inject
    public ListQuotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_quotes, container, false);

        ButterKnife.bind(this, view);
        mQuotesList.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadItems();
    }

    @Override
    public void setPresenter(QuotesListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showQuotes(List<Quote> quotes) {
        getActivity().runOnUiThread(() -> {
                mAdapter.clear();
                mAdapter.addAll(quotes);

        });
    }

    @Override
    public void showEmptyQuotesList() {
        getActivity().runOnUiThread(() -> {
            Toast.makeText(
                    getContext(),
                    "Quote list is empty",
                    Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showDialogBox(Quote quote) {

        FragmentManager manager = getFragmentManager();
        mDialogFragment.getDialogPresenter()
                .setSelectedQuoteID(quote.getId());
        mDialogFragment.show(manager, "Alert Dialog");
    }

    @Override
    public void showQuoteDetails(Quote quote) {
        Intent intent = new Intent(getContext(), QuoteDetailsActivity.class);
        intent.putExtra(Constants.SELECTED_QUOTE, quote);
        startActivity(intent);
    }

    @Override
    public void showError(Throwable e) {
        getActivity().runOnUiThread( () -> {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showLoading() {

        getActivity().runOnUiThread(() -> {
            mQuotesList.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        getActivity().runOnUiThread(() -> {
            mQuotesList.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        });
    }

    @OnItemLongClick(R.id.lv_quotes)
    public boolean onItemLongClick (int position) {
       Quote quote = (Quote) mAdapter.getItem(position);
       mPresenter.selectOnLong(quote);
       return true;
    }

    @OnItemClick(R.id.lv_quotes)
    public void onItemClick(int position) {
        Quote quote = (Quote) mAdapter.getItem(position);
        mPresenter.selectQuote(quote);
    }

    @Override
    public void setDialog(UpdateDeleteDialogFragment dialog) {
        this.mDialogFragment = dialog;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String pattern = s.toLowerCase();
        mPresenter.filterQuotes(pattern);
        return true;
    }
}
