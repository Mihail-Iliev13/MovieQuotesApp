package com.example.demo.repositories;

import com.example.demo.models.Quote;
import com.example.demo.repositories.base.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MyDB implements QuotesRepository {

    @Autowired
    private ConnectionHelper connectionHelper;



//    {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/mydb","root","44711393201");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public List<Quote> getQuotes() {
        List<Quote> quoteList = null;
        try(

            Connection connection = connectionHelper.getConnection();
            Statement query = connection.createStatement();
            ResultSet resultSet = query.executeQuery(getQuotesQuery());

        ){
            quoteList = readQuoteEntityData(resultSet);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
            return quoteList;
    }

    @Override
    public Set<String> getMovies() {
        Set<String> movies = null;
        try(
                Connection connection = connectionHelper.getConnection();
                Statement query = connection.createStatement();
                ResultSet resultSet = query.executeQuery(getMoviesQuery());

        ){
            movies = readLookUpTableData(resultSet);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return movies;
    }

    @Override
    public Set<String> getCharacters() {
        Set<String> characters = null;
        try(
                Connection connection = connectionHelper.getConnection();
                Statement query = connection.createStatement();
                ResultSet resultSet = query.executeQuery(getCharactersQuery());

        ){
            characters = readLookUpTableData(resultSet);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return characters;
    }



    @Override
    public void updateQuote(int id, Quote newQuote) {

        try(
                Connection connection = connectionHelper.getConnection();
                PreparedStatement updateQuery = connection.prepareStatement(getUpdateQuoteQuery());
        ){
            updateQuery.setString(1, newQuote.getQuoteText());
            updateQuery.setInt(2, getMovieID(newQuote.getMovie()));
            updateQuery.setInt(3, getCharacterID(newQuote.getQuotedCharacter()));
            updateQuery.setFloat(4, newQuote.getRating());
            updateQuery.setInt(5, id);
            updateQuery.executeUpdate();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void deleteQuote(int id) {

        try(
                Connection connection = connectionHelper.getConnection();
                PreparedStatement deleteQuote = connection.prepareStatement(getDeleteQuoteQuery())
        ){
            deleteQuote.setInt(1, id);
            deleteQuote.execute();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertIntoMoviesTable(String movie) {

        try(
                Connection connection = connectionHelper.getConnection();
                PreparedStatement insertMovieQuery = connection.prepareStatement(getInsertMovieQuery())
        ){
            insertMovieQuery.setString(1, movie);
            insertMovieQuery.execute();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void insertIntoCharactersTable(String character) {
        try(
                Connection connection = connectionHelper.getConnection();
                PreparedStatement insertCharacterQuery = connection.prepareStatement(getInsertCharacterQuery())
        ){
            insertCharacterQuery.setString(1, character);
            insertCharacterQuery.execute();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insertIntoQuotesTable(Quote quote) {
        try {
                Connection connection = connectionHelper.getConnection();
                PreparedStatement insertQuoteQuery = connection.prepareStatement(getInsertQuoteQuery());
                insertQuoteQuery.setString(1, quote.getQuoteText());
                insertQuoteQuery.setInt(2, getMovieID(quote.getMovie()));
                insertQuoteQuery.setInt(3, getCharacterID(quote.getQuotedCharacter()));
                insertQuoteQuery.setFloat(4, quote.getRating());
                insertQuoteQuery.executeUpdate();
                connection.close();
                } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getCharacterID(String quotedCharacter) throws SQLException {
        int id = 0;
        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement getCharacterIDQuery = connection.prepareStatement(getGetCharacterIDQuery());
        ){
            getCharacterIDQuery.setString(1, quotedCharacter);
            ResultSet resultSet = getCharacterIDQuery.executeQuery();
            id = readID(resultSet);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public int getMovieID(String movie){
        int id = 0;
        try (
                Connection connection = connectionHelper.getConnection();
                PreparedStatement getMovieIDQuery = connection.prepareStatement(getGetMovieIDQuery());
        ){
            getMovieIDQuery.setString(1, movie);
            ResultSet resultSet = getMovieIDQuery.executeQuery();
            id = readID(resultSet);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    private List<Quote> readQuoteEntityData(ResultSet resultSet) throws SQLException {
        List<Quote> quoteList = new ArrayList<>();
        while (resultSet.next()) {
            int quoteID = resultSet.getInt(1);
            String quoteBody = resultSet.getString(2);
            String quoteMovie = resultSet.getString(3);
            String quotedCharacter = resultSet.getString(4);
            float rating = resultSet.getFloat(5);
            Quote currentQuote = new Quote(quoteID, quoteBody, quoteMovie, quotedCharacter, rating);
            quoteList.add(currentQuote);
        }
        return quoteList;
    }

    private Set<String> readLookUpTableData(ResultSet resultSet) throws SQLException {
        Set<String> entity = new HashSet<>();
        while (resultSet.next()) {
            entity.add(resultSet.getString(1));
        }
        return entity;
    }

    private String getQuotesQuery() {
        return "select q.quote_id, q.quote_body as Quote," +
                " m.movie_name as Movie," +
                "c.character_name as 'Character'," +
                "q.rating as Rating\n" +
                "from quotes as q \n" +
                "join movies as m on m.movie_id = q.movies_movie_id\n" +
                "join characters as c on c.character_id = q.characters_character_id;";
    }

    private int readID(ResultSet resultSet) throws SQLException {
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
    }

    private String getGetMovieIDQuery () {
        return "select movie_id from movies\n" +
                "where movie_name = ?";
    }
    private String getGetCharacterIDQuery() {
        return "select character_id from characters\n" +
                "where character_name = ?" ;
    }

    private String getMoviesQuery(){
      return "select movie_name from movies;";
    }

    private String getCharactersQuery() {
        return  "select character_name from characters;";
    }

    private String getUpdateQuoteQuery() {
        return "update quotes set quote_body = ?, movies_movie_id = ?," +
                "characters_character_id = ?, rating = ? where quote_id = ?";
    }

    private String getDeleteQuoteQuery() {
        return "delete from quotes where quote_id = ?";
    }

    private String getInsertMovieQuery() {
        return "insert into movies(movie_name) values (?)";
    }

    private String getInsertCharacterQuery() {
        return "insert into characters(character_name) values (?)";
    }

    private String getInsertQuoteQuery() {
        return "insert into quotes(quote_body, movies_movie_id, characters_character_id, rating)" +
                "values (?, ?, ?, ?)";
    }
}
