package com.example.mihai.moviequotesapp.models;

public class Quote {

    public int id;
    public String quoteText;
    public String movie;
    public String quotedCharacter;
    public int rating;

    public Quote() {

    }

    public Quote (String quoteText, String movie, String quotedCharachter, int rating) {
        this.quoteText = quoteText;
        this.movie = movie;
        this.quotedCharacter = quotedCharachter;
        this.rating = rating;
    }

    public String getText() {
        return quoteText;
    }

    public int getRating() {
        return rating;
    }
}
