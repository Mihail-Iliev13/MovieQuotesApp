package com.example.mihai.moviequotesapp.views.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.contracts.QuoteDetailsContracts;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuoteDetailsFragment extends Fragment implements QuoteDetailsContracts.View {

    @Inject
    QuoteDetailsContracts.Presenter mPresenter;

    @BindView(R.id.tv_quote_text)
    TextView mQuoteTextView;
    @BindView(R.id.tv_movie_body)
    TextView mMovie;
    @BindView(R.id.tv_quoted_character_body)
    TextView mQuotedCharacterView;
    @BindView(R.id.rb_rating)
    RatingBar mRating;
    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @Inject
    public QuoteDetailsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quote_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadQuote();
    }

    @Override
    public void showQuote(Quote quote) {
        runOnUi(() -> {
            String quoteText = "\"" + quote.getText() + "\"";
            mQuoteTextView.setText(quoteText);
            mMovie.setText(quote.getMovie());
            mQuotedCharacterView.setText(quote.getQuotedCharacter());
            mRating.setRating(quote.getRating());
        });
    }

    @Override
    public void setPresenter(QuoteDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }


    private void runOnUi(Runnable action) {
        getActivity().runOnUiThread(action);
    }
}
