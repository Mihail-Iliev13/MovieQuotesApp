package com.example.mihai.moviequotesapp;

public class Constants {
    public static final String BASE_SERVER_URL = "http://192.168.0.107:8080/api/quotes";

    public static final int MIN_QUOTE_BODY_LENGTH = 1;

    public static final int MIN_MOVIE_NAME_LENGTH = 1;
    public static final int MAX_MOVIE_NAME_LENGTH = 50;

    public static final int MIN_CHARACTER_NAME_LENGTH = 1;
    public static final int MAX_CHARACTER_NAME_LENGTH = 50;

    public static final int MIN_RATING = 0;
    public static final int MAX_RATING = 5;

    public static final int CREATE_ACTIVITY_ID = 1;
    public static final int QUOTES_LIST_ACTIVITY_ID = 2;
    public static final int MOVIES_LIST_ACTIVITY_ID = 3;


    public static final String SELECTED_QUOTE = "Selected";
}
