package com.example.demo.models;

public class QuoteDTO {

    private int id;
    private String quoteText;
    private String movie;
    private String quotedCharacter;
    private float rating;

    public QuoteDTO () {

    }

    public QuoteDTO(int id, String quoteText, String movie, String quotedCharacter, float rating) {
        this.id = id;
        this.quoteText = quoteText;
        this.movie = movie;
        this.quotedCharacter = quotedCharacter;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movieName) {
        this.movie = movieName;
    }

    public String getQuotedCharacter() {
        return quotedCharacter;
    }

    public void setQuotedCharacter(String quotedCharacter) {
        this.quotedCharacter = quotedCharacter;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
