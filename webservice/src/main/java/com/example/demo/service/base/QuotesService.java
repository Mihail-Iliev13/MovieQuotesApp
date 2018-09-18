package com.example.demo.service.base;

import com.example.demo.models.Quote;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface QuotesService {
    void createQuote(Quote quote) throws SQLException;
    List<Quote> getQuotes() throws SQLException;
    Quote getQuoteById(int id) throws SQLException;
    void updateQuote(int id,Quote quote) throws SQLException;
    void deleteQuote(int id) throws SQLException;
}
