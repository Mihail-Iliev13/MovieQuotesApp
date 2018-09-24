package com.example.mihai.moviequotesapp.views.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.activities.UpdateQuoteActivity;
import com.example.mihai.moviequotesapp.views.contracts.MovieListContracts;
import com.example.mihai.moviequotesapp.views.contracts.UpdateDeleteDialogContracts;
import com.example.mihai.moviequotesapp.views.contracts.baseListContracts.BaseListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateDeleteDialogFragment extends AppCompatDialogFragment
        implements UpdateDeleteDialogContracts.View {

    @BindView(R.id.btn_update)
    Button mUpdate;

    @BindView(R.id.btn_delete)
    Button mDelete;

    private UpdateDeleteDialogContracts.Presenter mPresenter;
    private BaseListPresenter mBasePresenter;

    @Inject
    public UpdateDeleteDialogFragment() {
        // Required empty public constructor
    }

    public static UpdateDeleteDialogFragment newInstance() {
        return new UpdateDeleteDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.update_or_delete, null);
        ButterKnife.bind(this, view);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    public void setListPresenter(BaseListPresenter presenter) {
        this.mBasePresenter = presenter;
    }


    @Override
    public void setPresenter(UpdateDeleteDialogContracts.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showUpdateActivity(Quote quote) {
        getActivity().runOnUiThread(this::dismiss);
        Intent intent = new Intent(getActivity(), UpdateQuoteActivity.class);
        intent.putExtra(Constants.SELECTED_QUOTE, quote);
        startActivity(intent);
    }


    @OnClick(R.id.btn_update)
    public void onUpdateClick(){
        mPresenter.update();
    }

    @OnClick(R.id.btn_delete)
    public void onDeleteClick(){
        mPresenter.delete();
        getActivity().runOnUiThread(this::dismiss);
        mBasePresenter.loadItems();
    }

    public UpdateDeleteDialogContracts.Presenter getDialogPresenter() {
        return mPresenter;
    }
}
