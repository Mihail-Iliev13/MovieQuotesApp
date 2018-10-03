package com.example.demo.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "quotes")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private int id;

    @NotNull(message = "Quote text must not be null")
    @Column(name = "quote_body", nullable = false)
    private String quoteText;


    @NotNull(message = "Quoted character must not be null")
    @ManyToOne(targetEntity = Character.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "characters_character_id")
    private Character quotedCharacter;

    @NotNull(message =  "Movie name must not be null")
    @JoinColumn(name = "movies_movie_id")
    @ManyToOne(targetEntity = Movie.class, cascade = CascadeType.ALL)
    private Movie movie;


    @Min(value = 0, message = "Rating must be positive")
    @Max(value = 5, message = "Rating must be below 5")

    @Column(name = "rating")
    private float rating;

    public Quote(){

    }

    public Quote(int quoteID, String quoteBody, Movie quoteMovie, Character quotedCharacter, float rating){
        setId(quoteID);
        setQuoteText(quoteBody);
        setMovie(quoteMovie);
        setQuotedCharacter(quotedCharacter);
        setRating(rating);
    }

    public Quote(String quoteText, Movie movie, Character quotedCharacter, float rating) {
        this.quoteText = quoteText;
        this.movie = movie;
        this.quotedCharacter = quotedCharacter;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public Character getQuotedCharacter() {
        return quotedCharacter;
    }

    public Movie getMovie() {
        return movie;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setQuotedCharacter(Character quotedCharacter) {
        this.quotedCharacter = quotedCharacter;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Quote)) {
           return false;
       }
       Quote quote = (Quote) obj;

       return quotedCharacter.equals(quote.quotedCharacter)
               && quoteText.equals(quote.getQuoteText())
               && movie.equals(quote.movie)
               && Float.compare(rating, quote.getRating()) == 0;
    }

    @Override
    public int hashCode() {
        int result = quoteText.hashCode();
        result = result * 31 * movie.hashCode();
        result = result * 31 * quotedCharacter.hashCode();
        result = result * 31 * Float.hashCode(rating);
        return result;
    }
}
