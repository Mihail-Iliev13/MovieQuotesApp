package com.example.demo.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Quote {

    @Positive (message = "id should be positive number")
    private int id;

    @NotNull(message = "Quote text must not be null")
    private String quoteText;

    @NotNull(message =  "Movie name must not be null")
    private String movie;

    @NotNull(message = "Quoted character must not be null")
    private String quotedCharacter;

    @Min(value = 0, message = "Rating must be positive")
    @Max(value = 5, message = "Rating must be below 5")
    private float rating;

public Quote(){
    //def constructor required
}

    public Quote(String quoteText, String movie, String quotedCharacter, int rating) {
        this.quoteText = quoteText;
        this.movie = movie;
        this.quotedCharacter = quotedCharacter;
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getQuotedCharacter() {
        return quotedCharacter;
    }

    public void setQuotedCharacter(String quotedCharacter) {
        this.quotedCharacter = quotedCharacter;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
