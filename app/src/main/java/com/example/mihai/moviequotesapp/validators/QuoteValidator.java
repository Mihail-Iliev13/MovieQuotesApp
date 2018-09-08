package com.example.mihai.moviequotesapp.validators;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.validators.base.Validator;

public class QuoteValidator implements Validator {

    @Override
    public boolean isValid(Quote quote) throws IllegalArgumentException{
        return isQuoteBodyLengthValid(quote) && isMovieNameLengthValid(quote) &&
                isCharacterNameValid(quote) && isRatingValid(quote);

    }

    private boolean isQuoteBodyLengthValid(Quote quote) {

        int quoteBodyLength = quote.getText().length();
        return quoteBodyLength >= Constants.MIN_QUOTE_BODY_LENGTH;
    }

    private boolean isMovieNameLengthValid(Quote quote){
        int movieNameLength = quote.getMovie().length();

        return movieNameLength >= Constants.MIN_MOVIE_NAME_LENGTH
                && movieNameLength <= Constants.MAX_MOVIE_NAME_LENGTH;
    }

    private boolean isCharacterNameValid(Quote quote) {
        int characterNameLength = quote.getQuotedCharacter().length();

        return characterNameLength >= Constants.MIN_CHARACTER_NAME_LENGTH
                && characterNameLength <= Constants.MAX_CHARACTER_NAME_LENGTH;
    }

    private boolean isRatingValid (Quote quote) {
        int rating = (int) quote.getRating();

        return rating >= Constants.MIN_RATING && rating <= Constants.MAX_RATING;
    }
}
