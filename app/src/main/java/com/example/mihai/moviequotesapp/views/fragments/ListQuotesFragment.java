package com.example.mihai.moviequotesapp.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mihai.moviequotesapp.QuotesAdapter;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.contracts.ListAllQuotesContracts;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


public class ListQuotesFragment extends Fragment implements ListAllQuotesContracts.View {

    @BindView(R.id.lv_quotes)
    ListView mQuotesList;

    @BindView(R.id.et_search)
    EditText mSearch;

    @Inject
    public QuotesAdapter mAdapter;

    private ListAllQuotesContracts.Presenter mPresenter;

    @Inject
    public ListQuotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_quotes, container, false);

        ButterKnife.bind(this, view);
        mQuotesList.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadQuotes();
    }

    @Override
    public void setPresenter(ListAllQuotesContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showQuotes(List<Quote> quotes) {
        getActivity().runOnUiThread(() -> {
            mAdapter.clear();
            mAdapter.addAll(quotes);
        });
    }

    @OnTextChanged(R.id.et_search)
    public void onTextChanged() {
        String pattern = mSearch.getText().toString();
        mPresenter.filterQuotes(pattern);
    }

}
