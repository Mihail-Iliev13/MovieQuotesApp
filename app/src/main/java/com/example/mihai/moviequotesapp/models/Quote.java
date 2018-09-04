package com.example.mihai.moviequotesapp.models;

public class Quote {

    public int id;
    public String quoteText;
    public String movie;
    public String quotedCharacter;
    public float rating;

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
}
