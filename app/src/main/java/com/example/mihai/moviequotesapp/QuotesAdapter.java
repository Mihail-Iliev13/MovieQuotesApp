package com.example.mihai.moviequotesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mihai.moviequotesapp.models.Quote;

import java.util.List;

public class QuotesAdapter extends ArrayAdapter {

    private int mLayout;

    public QuotesAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mLayout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mainViewHolder;
        Quote quote = (Quote) getItem(position);

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(mLayout, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);

            viewHolder.showQuoteText(quote);
            viewHolder.showQuoteRating(quote);

            convertView.setTag(viewHolder);

        } else {

            mainViewHolder = (ViewHolder) convertView.getTag();
            mainViewHolder.showQuoteText(quote);
            mainViewHolder.showQuoteRating(quote);
        }

        return convertView;
    }


    private class ViewHolder {

        private TextView quoteText;
        private RatingBar ratingBar;

        private ViewHolder () {

        }

        private ViewHolder(View view) {
            this.quoteText = view.findViewById(R.id.tv_quote_text);
            this.ratingBar = view.findViewById(R.id.rb_quote_rating);

        }

        private void showQuoteText (Quote quote) {
            String text = "\"" + quote.getText() + "\"";
            this.quoteText.setText(text);
        }

        private void showQuoteRating(Quote quote) {
            this.ratingBar.setRating(quote.getRating());
        }
    }

}