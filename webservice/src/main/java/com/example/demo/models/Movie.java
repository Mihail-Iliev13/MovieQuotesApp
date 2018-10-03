package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @OneToMany(mappedBy = "movie")
    private List<Quote> quoteList;

    public Movie() {

    }

    public Movie (String movieName) {
        this.movieName = movieName;
    }


    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    @JsonIgnore
    public List<Quote> getQuoteList() {
        return quoteList;
    }

    private void setQuoteList(List<Quote> quoteList) {
        this.quoteList = quoteList;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Movie)) {
            return false;
        }
        Movie movie = (Movie)obj;
        return movieName.equals(movie.getMovieName());
    }

    @Override
    public int hashCode() {
        return movieName.hashCode();
    }
}
