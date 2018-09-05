package com.example.mihai.moviequotesapp.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

import javax.inject.Inject;

public class Quote implements Comparable<Quote>, Serializable{

    public int id;
    public String quoteText;
    public String movie;
    public String quotedCharacter;
    public float rating;

    @Inject
    public Quote() {

    }

    public Quote (String quoteText, String movie, String quotedCharacter, float rating) {
        this.quoteText = quoteText;
        this.movie = movie;
        this.quotedCharacter = quotedCharacter;
        this.rating = rating;
    }

    public String getText() {
        return quoteText;
    }

    public float getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public String getQuotedCharacter() {
        return quotedCharacter;
    }

    @Override
    public int compareTo(@NonNull Quote quote2) {
        if (getRating() > quote2.getRating()) {
            return 1;
        } else if (getRating() < quote2.getRating()) {
            return -1;
        }
        return 0;
    }
}
