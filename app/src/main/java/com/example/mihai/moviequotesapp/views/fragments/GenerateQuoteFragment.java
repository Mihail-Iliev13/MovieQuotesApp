package com.example.mihai.moviequotesapp.views.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.activities.CreateQuoteActivity;
import com.example.mihai.moviequotesapp.views.activities.UpdateQuoteActivity;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerateQuoteFragment extends Fragment implements GenerateQuoteContracts.View{

    @BindView(R.id.et_quote_text)
    EditText mQuoteText;
    @BindView(R.id.et_movie)
    EditText mMovie;
    @BindView(R.id.et_character)
    EditText mQuotedCharacter;
    @BindView(R.id.rb_rating)
    RatingBar mQuoteRating;
    @BindView(R.id.btn_button)
    Button mButton;

    private GenerateQuoteContracts.Presenter mPresenter;

    @Inject
    public GenerateQuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_create_quote, container, false);
        ButterKnife.bind(this, view);

        mButton.setOnClickListener(button -> {
            try {
                mPresenter.generateQuote();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return view;
    }

    @Override
    public void setPresenter(GenerateQuoteContracts.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void showToast(Quote quote) {

        getActivity().runOnUiThread(() -> {
            Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    public String getQuoteBody() {
        return mQuoteText.getText().toString();
    }

    @Override
    public String getQuoteMovie() {
        return mMovie.getText().toString();
    }

    @Override
    public String getQuotedCharacter() {
        return mQuotedCharacter.getText().toString();
    }

    @Override
    public float getRating() {
        return mQuoteRating.getRating();
    }

    @Override
    public void setQuoteBody(String quoteBody) {
        mQuoteText.setText(quoteBody);
    }

    @Override
    public void setQuoteMovie(String movie) {
        mMovie.setText(movie);
    }

    @Override
    public void setQuotedCharacter(String quotedCharacter) {
        mQuotedCharacter.setText(quotedCharacter);
    }

    @Override
    public void setRating(float rating) {
        mQuoteRating.setRating(rating);
    }

    @Override
    public void makeButtonBlue() {
        mButton.setBackgroundColor(Color.BLUE);
        mButton.setText("Update");
    }

}
