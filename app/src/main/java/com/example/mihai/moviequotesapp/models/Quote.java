package com.example.mihai.moviequotesapp.models;

public class Quote {

    public String quote;
    public String movie;
    public String quotedCharacter;
    public int rating;

    public Quote (String quote, String movie, String quotedCharachter, int rating) {
        this.quote = quote;
        this.movie = movie;
        this.quotedCharacter = quotedCharachter;
        this.rating = rating;
    }

}
