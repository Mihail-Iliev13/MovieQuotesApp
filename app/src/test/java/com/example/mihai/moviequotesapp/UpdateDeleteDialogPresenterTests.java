package com.example.mihai.moviequotesapp;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.HttpService;
import com.example.mihai.moviequotesapp.views.fragments.UpdateDeleteDialogFragment;
import com.example.mihai.moviequotesapp.views.presenters.UpdateDeleteDialogPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class UpdateDeleteDialogPresenterTests {

    @Mock
    private AsyncRunner mockAsyncRunner;
    @Mock
    private HttpService mockService;
    private UpdateDeleteDialogFragment mockView = Mockito.mock(UpdateDeleteDialogFragment.class);
    @InjectMocks
    private UpdateDeleteDialogPresenter presenter;

    @Test
    public void update_should_showUpdateActivity() throws IOException {
        presenter.setView(mockView);
        presenter.setSelectedQuoteID(1);
        Quote quote = new Quote("aaa", "bbb", "ccc", 2);
        quote.setId(1);

        Mockito.when(mockService.getQuoteByID(1))
                .thenReturn(quote);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        presenter.update();
        Mockito.verify(mockView).showUpdateActivity(quote);
    }

    @Test
    public void update_should_throwException_when_serviceIsBroken() throws IOException {
        presenter.setView(mockView);
        presenter.setSelectedQuoteID(1);
        Quote quote = new Quote("aaa", "bbb", "ccc", 2);
        quote.setId(1);

        Mockito.when(mockService.getQuoteByID(1))
                .thenThrow(IOException.class);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        presenter.update();
    }

    @Test
    public void delete_should_callServiceMethodsForDeleting() throws IOException {
        //Arrange
        presenter.setSelectedQuoteID(1);
        presenter.setView(mockView);
        Quote quote = new Quote("aaa", "bbb", "ccc", 2);
        quote.setId(1);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        Mockito.when(mockService.getQuoteByID(1)).thenReturn(quote);

        //Act
        presenter.delete();
        Mockito.verify(mockService).getQuoteByID(1);
        Mockito.verify(mockService).deleteQuote(quote);
    }


    @Test
    public void delete_should_throwException_when_serviceIsBroken() throws IOException {
        //Arrange
        presenter.setSelectedQuoteID(1);
        presenter.setView(mockView);
        Quote quote = new Quote("aaa", "bbb", "ccc", 2);
        quote.setId(1);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());

        Mockito.when(mockService.getQuoteByID(1)).thenThrow(IOException.class);

        //Act
        presenter.delete();
    }

}
