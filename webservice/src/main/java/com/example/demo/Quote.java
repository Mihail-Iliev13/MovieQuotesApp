package com.example.demo;

public class Quote {

    private int id;
    private String quoteText;
    private String movie;
    private String quotedCharacter;
    private int rating;

public Quote(){
    //def constructor required
}

    public Quote(int id, String quoteText, String movie, String quotedCharacter, int rating) {
        this.id = id;
        this.quoteText = quoteText;
        this.movie = movie;
        this.quotedCharacter = quotedCharacter;
        this.rating = rating;
    }

    public int getRating() {
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
