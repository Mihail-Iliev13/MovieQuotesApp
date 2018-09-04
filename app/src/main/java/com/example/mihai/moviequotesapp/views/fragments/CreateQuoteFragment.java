package com.example.mihai.moviequotesapp.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.mihai.moviequotesapp.R;

import butterknife.BindView;

public class CreateQuoteFragment extends Fragment {

    @BindView(R.id.et_quote_text)
    EditText quoteText;
    @BindView(R.id.et_movie)
    EditText movie;
    @BindView(R.id.et_character)
    EditText qutedCharacter;
    @BindView(R.id.rb_rating)
    RatingBar quoteRating;
    @BindView(R.id.btn_button)
    Button button;

    public CreateQuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_quote, container, false);

        return view;
    }

}
