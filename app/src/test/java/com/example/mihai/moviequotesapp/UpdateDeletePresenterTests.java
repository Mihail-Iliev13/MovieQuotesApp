package com.example.mihai.moviequotesapp;

import com.example.mihai.moviequotesapp.async.AsyncRunnerImpl;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.HttpService;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteButtonsFragment;
import com.example.mihai.moviequotesapp.views.presenters.UpdateDeletePresenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class UpdateDeletePresenterTests {

    @Mock
    private AsyncRunnerImpl mockAsyncRunner;

    @Mock
    private HttpService mockService;

    private UpdateDeleteButtonsFragment mockVIew = Mockito.mock(UpdateDeleteButtonsFragment.class);

    @InjectMocks
    private UpdateDeletePresenter presenter;

    @Before
    public void setUp () {
        presenter.setView(mockVIew);
        presenter.setSelectedQuoteID(1);
    }

    @Test
    public void updateMethod_should_openUpdateActivity () throws IOException {
        //Arrange
        String quoteText = "aaa";
        String movie = "bbb";
        String character = "ccc";
        float rating = 2;
        Quote fakeQuote = new Quote(quoteText, movie, character, rating);
        fakeQuote.setId(1);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());
       Mockito.when(mockService.getQuoteByID(1))
               .thenReturn(fakeQuote);

        //Act
        presenter.update();

        //Assert
        Mockito.verify(mockVIew).showUpdateActivity(fakeQuote);
    }


    @Test
    public void updateMethod_should_throwException_when_serviceIsBroken () throws IOException {
        //Arrange

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        Mockito.when(mockService.getQuoteByID(1))
                .thenThrow(Exception.class);

        //ActAssert
        presenter.update();
    }

    @Test
    public void delete_should_endActivity_when_quoteIsDeleted () throws IOException {
        //Arrange
        String quoteText = "aaa";
        String movie = "bbb";
        String character = "ccc";
        float rating = 2;
        Quote fakeQuote = new Quote(quoteText, movie, character, rating);
        fakeQuote.setId(1);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        Mockito.when(mockService.getQuoteByID(1))
                .thenReturn(fakeQuote);

        //Act
        presenter.delete();

        //Assert
        Mockito.verify(mockVIew).endActivity();
    }


    @Test
    public void delete_should_throwException_when_serviceIsBroken () throws IOException {
        //Arrange

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        Mockito.when(mockService.getQuoteByID(1))
                .thenThrow(IOException.class);

        //ActAssert
        presenter.delete();
    }
}
