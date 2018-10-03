package com.example.demo.repositories;

import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.Quote;
import com.example.demo.repositories.base.QuotesRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class MyDBRepo implements QuotesRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Quote> getQuotes() {

        List<Quote> list;
        try (
                Session session = sessionFactory.openSession()
                ) {

            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Quote> criteria = criteriaBuilder.createQuery(Quote.class);

            criteria.from(Quote.class);

            list = session.createQuery(criteria)
                    .getResultList();

            session.getTransaction().commit();
        }

        return list;
    }

    @Override
    public Quote updateQuote(int id, Quote updatedQuote) {
        Quote quoteToBeUpdated = null;
        try (
                Session session = sessionFactory.openSession()
        ){
            session.beginTransaction();
            quoteToBeUpdated = session.get(Quote.class, id);

            quoteToBeUpdated.setQuoteText(updatedQuote.getQuoteText());

            if (shouldUpdateMovie(updatedQuote.getMovie(),
                    quoteToBeUpdated.getMovie())) {
                quoteToBeUpdated.setMovie(updatedQuote.getMovie());
            }

            if (shouldUpdateCharacter(updatedQuote.getQuotedCharacter(),
                    quoteToBeUpdated.getQuotedCharacter())) {
                quoteToBeUpdated.setQuotedCharacter(updatedQuote.getQuotedCharacter());
            }

            quoteToBeUpdated.setRating(updatedQuote.getRating());
            session.update(quoteToBeUpdated);
            session.getTransaction().commit();
        }
        return quoteToBeUpdated;
    }

    @Override
    public Quote deleteQuote(int id) {
        Quote quoteToBeDeleted = null;
        try (
                Session session = sessionFactory.openSession()
        ){
            session.beginTransaction();
            quoteToBeDeleted = session.get(Quote.class, id);
            quoteToBeDeleted.setMovie(null);
            quoteToBeDeleted.setQuotedCharacter(null);
            session.delete(quoteToBeDeleted);
            session.getTransaction().commit();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return quoteToBeDeleted;
    }

    @Override
    public Object insert(Object object) {

      try (
           Session session = sessionFactory.openSession()
              ){
          session.beginTransaction();
          session.save(object);
          session.getTransaction().commit();
      }
      return object;
    }

    @Override
    public int getMovieID(Movie quoteMovie) {
        Movie movie = getMovieByName(quoteMovie.getMovieName());
        return movie.getId();
    }

    @Override
    public int getCharacterID(Character quotedCharacter) {
       Character character = getCharacterByName(quotedCharacter.getCharacterName());
       return character.getId();
    }

    @Override
    public Movie getMovieByName(String movieName) {

        Movie movie;

        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            Query query = session.createQuery("from Movie where movieName = :movieName");
            query.setParameter("movieName", movieName);
            movie = (Movie) query.getSingleResult();
            session.getTransaction()
                    .commit();
        } catch (NoResultException e) {
            return null;
        }

        return movie;
    }

    @Override
    public Character getCharacterByName(String quotedCharacterName) {

        Character character;

        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            Query query = session.createQuery("from Character where characterName = :characterName");
            query.setParameter("characterName", quotedCharacterName);
            character = (Character) query.getSingleResult();
            session.getTransaction()
                    .commit();
        } catch (NoResultException e) {
            return null;
        }

        return character;

    }

    private boolean shouldUpdateMovie(Movie movie, Movie movie1) {
        return !movie.getMovieName().equals(movie1.getMovieName());
    }


    private boolean shouldUpdateCharacter(Character character, Character quotedCharacter) {
        return !character.getCharacterName().equals(quotedCharacter.getCharacterName());
    }
}
