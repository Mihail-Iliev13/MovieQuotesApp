package com.example.mihai.moviequotesapp.views.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.QuotesAdapter;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.activities.QuoteDetailsActivity;
import com.example.mihai.moviequotesapp.views.activities.UpdateQuoteActivity;
import com.example.mihai.moviequotesapp.views.contracts.ListAllQuotesContracts;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnTextChanged;


public class ListQuotesFragment extends Fragment implements ListAllQuotesContracts.View {

    @BindView(R.id.lv_quotes)
    ListView mQuotesList;

    @BindView(R.id.et_search)
    EditText mSearch;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @Inject
    public QuotesAdapter mAdapter;

    private ListAllQuotesContracts.Presenter mPresenter;

    private Quote mSelectedQuote;

    private AlertDialog mUpdateOrDeleteDialog;

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
    public void showDialogBox() {

        View dialogBox = getActivity().getLayoutInflater().inflate(R.layout.update_or_delete_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogBox);

        mUpdateOrDeleteDialog = builder.create();
        mUpdateOrDeleteDialog.show();

        Button update = dialogBox.findViewById(R.id.btn_update);

        update.setOnClickListener(updateButton -> {
            mPresenter.navigateToUpdate();
        });

        Button delete = dialogBox.findViewById(R.id.btn_delete);

        delete.setOnClickListener(remove -> {
            mPresenter.deleteQuote(mSelectedQuote);
            mPresenter.loadQuotes();
            getActivity().runOnUiThread(() -> {
                mUpdateOrDeleteDialog.hide();
            });
        });
    }

    @Override
    public void showToast() {
        getActivity().runOnUiThread(() -> {
            Toast.makeText(getContext(), "Success!", Toast.LENGTH_LONG)
                    .show();
        });
    }

    @Override
    public void showUpdateActivity() {
        Intent intent = new Intent(getContext(), UpdateQuoteActivity.class);
        intent.putExtra(Constants.SELECTED_QUOTE, mSelectedQuote);
        startActivity(intent);

        getActivity().runOnUiThread(() -> {
            mUpdateOrDeleteDialog.hide();
            mAdapter.clear();
        });

    }

    @Override
    public void showQuoteDetails(Quote quote) {
        Intent intent = new Intent(getContext(), QuoteDetailsActivity.class);
        intent.putExtra(Constants.SELECTED_QUOTE, quote);
        startActivity(intent);
    }

    @Override
    public void showError(IOException e) {
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

    @OnTextChanged(R.id.et_search)
    public void onTextChanged() {
        String pattern = mSearch.getText().toString();
        mPresenter.filterQuotes(pattern);
    }

    @OnItemLongClick(R.id.lv_quotes)
    public boolean onItemLongClick (int position) {
       mSelectedQuote = (Quote) mAdapter.getItem(position);
       mPresenter.selectOnLong(mSelectedQuote);
       return true;
    }

    @OnItemClick(R.id.lv_quotes)
    public void onItemClick(int position) {
        mSelectedQuote = (Quote) mAdapter.getItem(position);
        mPresenter.selectQuote(mSelectedQuote);
    }
}
