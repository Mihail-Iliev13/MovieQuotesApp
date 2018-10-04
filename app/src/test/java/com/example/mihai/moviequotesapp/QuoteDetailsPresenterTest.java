package com.example.mihai.moviequotesapp;

import com.example.mihai.moviequotesapp.async.AsyncRunnerImpl;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.services.HttpService;
import com.example.mihai.moviequotesapp.views.fragments.QuoteDetailsFragment;
import com.example.mihai.moviequotesapp.views.presenters.QuoteDetailsPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QuoteDetailsPresenterTest {

  @Mock
  private AsyncRunnerImpl mockAsyncRunner;

  @Mock
  private HttpService mockService;

  private QuoteDetailsFragment mockView = Mockito.mock(QuoteDetailsFragment.class);

  @InjectMocks
  private  QuoteDetailsPresenter presenter;

  @Test
  public void loadQuote_should_showQuoteOnlyOnce_when_showQuoteIsCalled () throws IOException {
      //Arrange
      presenter.setSelectedQuoteID(1);
      presenter.setView(mockView);

      Quote quote1 = new Quote("aaa", "bbb", "ccc", 2);
      quote1.setId(1);

      Quote quote2 = new Quote("bbb", "ccc", "ddd", 3);
      quote2.setId(2);

      Quote quote3 = new Quote("ccc", "ddd", "eee", 4);
      quote3.setId(3);
      List<Quote> quoteList = new ArrayList<>();
      quoteList.add(quote1);
      quoteList.add(quote2);
      quoteList.add(quote3);


      Mockito.when(mockService.getAll()).thenReturn(quoteList);
      Mockito.doAnswer(invocation -> {
          Runnable action = (Runnable) invocation.getArguments()[0];
          action.run();
          return null;
      }).when(mockAsyncRunner)
              .runInBackground(Mockito.any());


      //Act
      presenter.loadQuote();

      //Assert
      Mockito.verify(mockView, Mockito.times(1)).showQuote(quote1);
  }


    @Test
    public void loadQuote_should_throwException_when_serviceIsBroken () throws IOException {
        //Arrange
        presenter.setSelectedQuoteID(1);
        presenter.setView(mockView);


        Mockito.when(mockService.getAll()).thenThrow(IOException.class);

        Mockito.doAnswer(invocation -> {
            Runnable action = (Runnable) invocation.getArguments()[0];
            action.run();
            return null;
        }).when(mockAsyncRunner)
                .runInBackground(Mockito.any());


        //Act
        presenter.loadQuote();
    }

}
