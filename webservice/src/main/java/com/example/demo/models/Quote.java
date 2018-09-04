package com.example.demo.models;

public class Quote {

    private int id;
    private String quoteText;
    private String movie;
    private String quotedCharacter;
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
