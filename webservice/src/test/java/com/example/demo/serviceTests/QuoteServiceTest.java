package com.example.demo.serviceTests;

import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.Quote;
import com.example.demo.models.QuoteDTO;
import com.example.demo.repositories.base.QuotesRepository;
import com.example.demo.service.QuotesServiceImpl;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuoteServiceTest {
    private QuotesRepository repository = Mockito.mock(QuotesRepository.class);
    private QuotesServiceImpl service = new QuotesServiceImpl(repository);

    @Test
    public void should_return_correct_Quote_object_when_QuoteDTO_is_passed() {
        //Arrange
        QuoteDTO quoteDTO = new QuoteDTO(1, "I'll be back", "Terminator",
                "Terminator", 2);
        Quote expectedQuote = new Quote();
        expectedQuote.setId(1);
        expectedQuote.setQuoteText("I'll be back");
        expectedQuote.setMovie(new Movie("Terminator"));
        Character character = new Character("Terminator");
        expectedQuote.setQuotedCharacter(character);
        expectedQuote.setRating(2);

        //Act
        Quote actualQuote = service.transformFromDTOToQuote(quoteDTO);

        //Assert
        assertEquals(actualQuote, expectedQuote);
    }

    @Test
    public void should_return_List_of_Quotes_when_List_of_QuoteDTOs_is_passed() {
        //Arrange
        Quote quote1 = new Quote("aaa", new Movie("aaa"), new Character("aaa"), 2);
        quote1.setId(1);
        Quote quote2 = new Quote("bbb", new Movie("bbb"), new Character("bbb"), 2);
        quote2.setId(2);
        Quote quote3 = new Quote("ccc", new Movie("ccc"), new Character("ccc"), 2);
        quote3.setId(3);

        List<Quote> quotes = new ArrayList<>();
        quotes.add(quote1);
        quotes.add(quote2);
        quotes.add(quote3);
        float delta = 0.00f;

        //Act
        List<QuoteDTO> quoteDTOS = service.transformFromQuoteToDTOList(quotes);
        QuoteDTO quoteDTO1 = new QuoteDTO(1,"aaa", "aaa", "aaa", 2);
        QuoteDTO quoteDTO2 = new QuoteDTO(2,"bbb", "bbb", "bbb", 2);
        QuoteDTO quoteDTO3 = new QuoteDTO(3,"ccc", "ccc", "ccc", 2);

        //Assert
        assertEquals(quoteDTO1.getId(), quoteDTOS.get(0).getId());
        assertEquals(quoteDTO1.getQuoteText(), quoteDTOS.get(0).getQuoteText());
        assertEquals(quoteDTO1.getMovie(), quoteDTOS.get(0).getMovie());
        assertEquals(quoteDTO1.getQuotedCharacter(), quoteDTOS.get(0).getQuotedCharacter());
        assertEquals(quoteDTO1.getRating(), quoteDTOS.get(0).getRating(), delta);

        assertEquals(quoteDTO2.getId(), quoteDTOS.get(1).getId());
        assertEquals(quoteDTO2.getQuoteText(), quoteDTOS.get(1).getQuoteText());
        assertEquals(quoteDTO2.getMovie(), quoteDTOS.get(1).getMovie());
        assertEquals(quoteDTO2.getQuotedCharacter(), quoteDTOS.get(1).getQuotedCharacter());
        assertEquals(quoteDTO2.getRating(), quoteDTOS.get(1).getRating(), delta);

        assertEquals(quoteDTO3.getId(), quoteDTOS.get(2).getId());
        assertEquals(quoteDTO3.getQuoteText(), quoteDTOS.get(2).getQuoteText());
        assertEquals(quoteDTO3.getMovie(), quoteDTOS.get(2).getMovie());
        assertEquals(quoteDTO3.getQuotedCharacter(), quoteDTOS.get(2).getQuotedCharacter());
        assertEquals(quoteDTO3.getRating(), quoteDTOS.get(2).getRating(), delta);

    }

    @Test
    public void isMovieExisting_should_return_true_when_movie_exists() {
        //Arrange
        Movie movie = new Movie("aaaa");

        //Act
        Mockito.when(repository.getMovieByName(movie.getMovieName()))
                .thenReturn(movie);

        boolean isMovieExisting = service.isMovieExisting(movie);
        assertTrue(isMovieExisting);
    }

    @Test
    public void isCharacterExisting_should_return_true_when_character_exists() {
        //Arrange
        Character character = new Character("aaaa");

        //Act
        Mockito.when(repository.getCharacterByName(character.getCharacterName()))
                .thenReturn(character);

        boolean isCharacterExisting = service.isCharacterExisting(character);
        assertTrue(isCharacterExisting);
    }

    @Test
    public void delete_should_return_deleted_quote() throws SQLException {
        //Arrange
        Quote expected = new Quote("aaa", new Movie("bbbb"),
                new Character("ccc"), 2);
        expected.setId(1);

        //Act
        Mockito.when(repository.deleteQuote(1)).thenReturn(expected);
        Quote actual = service.deleteQuote(expected.getId());

        //Assert
        assertEquals(actual, expected);
    }

    @Test
    public void update_should_update_quote_text_when_quote_is_updated () throws SQLException {
        //Arrange
        QuoteDTO quoteDTO = new QuoteDTO(1, "aaa", "bbb", "ccc", 2);
        Quote expected = new Quote("aaa", new Movie("bbb"),
                new Character("ccc"), 2);
        expected.setId(1);


        Mockito.when(repository.updateQuote(quoteDTO.getId(), expected))
                .thenReturn(expected);

        Quote actual = service.updateQuote(quoteDTO.getId(), quoteDTO);

        assertEquals(actual.getQuoteText(), expected.getQuoteText());
    }

    @Test
    public void getById_should_return_QuoteDTO_when_QuoteID_is_passed () {
        //Arrange
        Quote quote1 = new Quote("aaa", new Movie("bbb"), new Character("ccc"), 2);
        Quote quote2 = new Quote("bbb", new Movie("ccc"), new Character("ddd"), 3);
        quote1.setId(1);
        quote2.setId(2);

        QuoteDTO expected = new QuoteDTO(1,"aaa", "bbb", "ccc", 2);

        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(quote1);
        quoteList.add(quote2);

        //Act
        Mockito.when(repository.getQuotes()).thenReturn(quoteList);
        QuoteDTO actual = service.getQuoteById(1);

        //Assert
        assertEquals(actual.getQuoteText(), expected.getQuoteText());
        assertEquals(actual.getQuotedCharacter(), expected.getQuotedCharacter());
        assertEquals(actual.getMovie(), expected.getMovie());
        assertEquals(actual.getRating(), expected.getRating(), 0.00);
    }

    @Test
    public void getQuotes_should_return_exact_Quote_DTO_copies_of_all_quotes () {
        //Arrange
        Quote quote1 = new Quote("aaa", new Movie("bbb"), new Character("ccc"), 2);
        Quote quote2 = new Quote("bbb", new Movie("ccc"), new Character("ddd"), 3);
        quote1.setId(1);
        quote2.setId(2);
        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(quote1);
        quoteList.add(quote2);

        QuoteDTO expected1 = new QuoteDTO(1,"aaa", "bbb", "ccc", 2);
        QuoteDTO expected2 = new QuoteDTO(2,"bbb", "ccc", "ddd", 3);

        //Act
        Mockito.when(repository.getQuotes()).thenReturn(quoteList);
        List<QuoteDTO> quoteDTOS = service.getQuotes();

        //Assert
        assertEquals(expected1.getId(), quoteDTOS.get(0).getId());
        assertEquals(expected1.getQuoteText(), quoteDTOS.get(0).getQuoteText());
        assertEquals(expected1.getMovie(), quoteDTOS.get(0).getMovie());
        assertEquals(expected1.getQuotedCharacter(), quoteDTOS.get(0).getQuotedCharacter());
        assertEquals(expected1.getRating(), quoteDTOS.get(0).getRating(), 0.00);

        assertEquals(expected2.getId(), quoteDTOS.get(1).getId());
        assertEquals(expected2.getQuoteText(), quoteDTOS.get(1).getQuoteText());
        assertEquals(expected2.getMovie(), quoteDTOS.get(1).getMovie());
        assertEquals(expected2.getQuotedCharacter(), quoteDTOS.get(1).getQuotedCharacter());
        assertEquals(expected2.getRating(), quoteDTOS.get(1).getRating(), 0.00);

    }

    @Test
    public void return_exact_Quote_when_new_Quote_is_created() throws SQLException {
        //Arrange
        QuoteDTO quoteDTO = new QuoteDTO(1, "aaa", "bbb", "ccc", 2);
        Quote expected = new Quote("aaa", new Movie("bbb"),
                new Character("ccc"), 2);
        expected.setId(1);

        //Act
        Mockito.when(repository.insert(expected)).thenReturn(expected);
        Quote actual = service.createQuote(quoteDTO);

        assertEquals(actual, expected);
    }

}
