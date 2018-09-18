package com.example.demo.repositories;

import com.example.demo.models.Quote;
import com.example.demo.repositories.base.QuotesRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class MyDB implements QuotesRepository {

    private List<Quote> quotes = new ArrayList<>();
    private Connection connection;

    public MyDB()

    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb","root","44711393201");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet getQuotes() {
        ResultSet resultSet = null;
        try{

            Statement query = connection.createStatement();
            resultSet = query.executeQuery("select q.quote_id, q.quote_body as Quote," +
                    " m.movie_name as Movie," +
                    "c.character_name as 'Character'," +
                    "q.rating as Rating\n" +
                    "from quotes as q \n" +
                    "join movies as m on m.movie_id = q.movies_movie_id\n" +
                    "join characters as c on c.character_id = q.characters_character_id;");
        }catch(Exception e){
            System.out.println(e.getMessage());}
            return resultSet;
    }

    @Override
    public ResultSet getMovies() {
        ResultSet resultSet = null;
        try{

            Statement query = connection.createStatement();
            resultSet = query.executeQuery("select movie_name from movies;");
        }catch(Exception e){
            System.out.println(e.getMessage());}
        return resultSet;
    }

    @Override
    public ResultSet getCharacters() {
        ResultSet resultSet = null;
        try{

            Statement query = connection.createStatement();
            resultSet = query.executeQuery("select character_name from characters;");
        }catch(Exception e){
            System.out.println(e.getMessage());}
        return resultSet;
    }


    @Override
    public void updateQuote(int id, Quote newQuote) throws SQLException {
        String quoteQuery = "update quotes set quote_body = ?, movies_movie_id = ?," +
                "characters_character_id = ?, rating = ? where quote_id = ?";

        PreparedStatement updateQuery = connection.prepareStatement(quoteQuery);
        updateQuery.setString(1, newQuote.getQuoteText());
        updateQuery.setInt(2, getMovieID(newQuote.getMovie()));
        updateQuery.setInt(3, getCharacterID(newQuote.getQuotedCharacter()));
        updateQuery.setFloat(4, newQuote.getRating());
        updateQuery.setInt(5, id);
        updateQuery.execute();
    }


    @Override
    public void deleteQuote(int id) throws SQLException {
        String deleteQuoteQuery = "delete from quotes where quote_id = ?";
        PreparedStatement deleteQuote = connection.prepareStatement(deleteQuoteQuery);
        deleteQuote.setInt(1, id);
        deleteQuote.execute();
    }

    @Override
    public void insertIntoMoviesTable(String movie) throws SQLException {
        String movieQuery = "insert into movies(movie_name) values (?)";
        PreparedStatement insertMovieQuery = connection.prepareStatement(movieQuery);
        insertMovieQuery.setString(1, movie);
        insertMovieQuery.execute();
    }

    @Override
    public void insertIntoCharactersTable(String character) throws SQLException {
        String characterQuery = "insert into characters(character_name) values (?)";
        PreparedStatement insertCharacterQuery = connection.prepareStatement(characterQuery);
        insertCharacterQuery.setString(1, character);
        insertCharacterQuery.execute();
    }

    @Override
    public void insertIntoQuotesTable(Quote quote) throws SQLException {
        String quoteQuery = "insert into quotes(quote_body, movies_movie_id, characters_character_id, rating)" +
                "values (?, ?, ?, ?)";

        PreparedStatement insertQuoteQuery = connection.prepareStatement(quoteQuery);
        insertQuoteQuery.setString(1, quote.getQuoteText());
        insertQuoteQuery.setInt(2, getMovieID(quote.getMovie()));
        insertQuoteQuery.setInt(3, getCharacterID(quote.getQuotedCharacter()));
        insertQuoteQuery.setFloat(4, quote.getRating());
        insertQuoteQuery.execute();
    }

    @Override
    public int getCharacterID(String quotedCharacter) throws SQLException {
        Statement getCharacterID = connection.createStatement();
        ResultSet resultSet = getCharacterID.executeQuery("select character_id from characters\n" +
                "where character_name = '"+ quotedCharacter + "'");

        int characterID = 0;
        while (resultSet.next()) {
            characterID = resultSet.getInt(1);
        }
        return characterID;
    }

    @Override
    public int getMovieID(String movie) throws SQLException {
        Statement getMovieID = connection.createStatement();
        ResultSet resultSet = getMovieID.executeQuery("select movie_id from movies\n" +
                "where movie_name = '"+ movie + "'");

        int movieID = 0;
        while (resultSet.next()) {
            movieID = resultSet.getInt(1);
        }

        return movieID;
    }

}
