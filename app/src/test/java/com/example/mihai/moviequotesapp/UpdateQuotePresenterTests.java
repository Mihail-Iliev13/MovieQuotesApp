package com.example.mihai.moviequotesapp;


import com.example.mihai.moviequotesapp.async.AsyncRunnerImpl;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.HttpService;
import com.example.mihai.moviequotesapp.views.fragments.GenerateQuoteFragment;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteButtonsFragment;
import com.example.mihai.moviequotesapp.views.presenters.UpdateDeletePresenter;
import com.example.mihai.moviequotesapp.views.presenters.UpdateQuotePresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class UpdateQuotePresenterTests {

    @Mock
    private AsyncRunnerImpl mockAsyncRunner;

    @Mock
    private HttpService mockService;

    private GenerateQuoteFragment mockVIew = Mockito.mock(GenerateQuoteFragment.class);

    @InjectMocks
    private UpdateQuotePresenter presenter;

    @Before
    public void setup () {
        presenter.setView(mockVIew);
        Quote quote = new Quote("aaa", "bbb", "ccc", 2);
        quote.setId(1);
        presenter.setSelectedQuote(quote);
    }

    @Test
    public void generateQuote_should_callShowToast_when_quoteIsGeneratedSuccessfully() throws IOException {
        //Arrange
        String text = "bbb";
        String movie = "ccc";
        String character = "ddd";
        float rating = 3;

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        //Act
        presenter.generateQuote(text, movie, character, rating);

        //Assert
        Mockito.verify(mockVIew).showToast();
    }

    @Test
    public void generateQuote_should_showErrorToast_when_illegalArgumentIsPassed() throws IOException {
        //Arrange
        Quote fakeQuote = new Quote();
        presenter.setSelectedQuote(fakeQuote);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        IllegalArgumentException e = new IllegalArgumentException();
        Mockito.doThrow(e).when(mockService)
                .updateQuote(fakeQuote);

        //Act
        presenter.generateQuote("aaa","fff","dss", 3);

        //Assert
        Mockito.verify(mockVIew).showError(e);
    }

    @Test
    public void generateQuote_should_throwError_when_serviceIsBroken() throws IOException {
        //Arrange
        Quote fakeQuote = new Quote();
        presenter.setSelectedQuote(fakeQuote);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        IOException e = new IOException();
        Mockito.doThrow(e).when(mockService)
                .updateQuote(fakeQuote);

        //ActAssert
        presenter.generateQuote("aaa","bbb", "ccc", 3);
        }

    @Test
    public void fillFields_should_setMockViewQuoteDetails_when_methodIsCalled () {
        //Arrange
        Quote fakeQuote = new Quote();
        presenter.setSelectedQuote(fakeQuote);

        //Act
        presenter.fillFields();

        //Assert
        Mockito.verify(mockVIew).setQuoteBody(fakeQuote.getText());
        Mockito.verify(mockVIew).setQuoteMovie(fakeQuote.getMovie());
        Mockito.verify(mockVIew).setQuotedCharacter(fakeQuote.getQuotedCharacter());
        Mockito.verify(mockVIew).setRating(fakeQuote.getRating());
        Mockito.verify(mockVIew).changeButton();

    }
}
