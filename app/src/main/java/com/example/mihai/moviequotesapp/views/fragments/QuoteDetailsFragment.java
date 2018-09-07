package com.example.mihai.moviequotesapp.views.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.QuoteDetailsContracts;


public class QuoteDetailsFragment extends Fragment implements QuoteDetailsContracts.View {
    private QuoteDetailsContracts.Presenter mPresenter;
    private TextView mQuoteTextView;
    private TextView mQuotedCharacterView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote_details, container, false);
    }

    @Override
    public void showQuote(Quote quote) {
        runOnUi(() -> {
            mQuoteTextView.setText(quote.quoteText);
            mQuotedCharacterView.setText(quote.quotedCharacter);
        });

    }

    @Override
    public void setPresenter(QuoteDetailsContracts.Presenter presenter) {
mPresenter = presenter;
    }
    private void runOnUi(Runnable action) {
        getActivity().runOnUiThread(action);
    }

    public static QuoteDetailsFragment newInstance() {
        return new QuoteDetailsFragment();
    }

}
