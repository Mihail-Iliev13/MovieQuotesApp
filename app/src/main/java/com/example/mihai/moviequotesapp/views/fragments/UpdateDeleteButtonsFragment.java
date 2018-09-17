package com.example.mihai.moviequotesapp.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.activities.UpdateQuoteActivity;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteButtonContracts;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateDeleteButtonsFragment extends Fragment implements UpdateDeleteButtonContracts.View{


    private UpdateDeleteButtonContracts.Presenter mPresenter;
    private Quote mSelectedQuote;

    @Inject
    public UpdateDeleteButtonsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.update_or_delete, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_update)
    public void onUpdateClick(){
        mPresenter.update();
    }


    @OnClick(R.id.btn_delete)
    public void onDeleteClick(){
        mPresenter.delete(mSelectedQuote);
    }

    @Override
    public void setPresenter(UpdateDeleteButtonContracts.Presenter presenter) {
        this.mPresenter = presenter;;
    }

    @Override
    public void showUpdateActivity() {
        Intent intent = new Intent(getContext(), UpdateQuoteActivity.class);
        intent.putExtra(Constants.SELECTED_QUOTE, mSelectedQuote);
        startActivity(intent);
    }

    @Override
    public void setSelectedQuote(Quote quote) {
        mSelectedQuote = quote;
    }
}
