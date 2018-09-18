package com.example.demo.controllers;

import com.example.demo.models.Quote;
import com.example.demo.service.base.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/quotes")
public class QuotesController {
    private QuotesService service;

    @Autowired
    public QuotesController(QuotesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Quote> getQuotes() {
        List<Quote> quoteList = null;
        try {
            quoteList = service.getQuotes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quoteList;
    }

    @GetMapping("/{id}")
    public Quote getQuoteById(@PathVariable int id) {
        Quote quote = null;
        try {
            quote = service.getQuoteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quote;
    }

    @PostMapping
    public void createQuote(@Valid @RequestBody Quote newQuote) {
        try {
            service.createQuote(newQuote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void updateQuote(@PathVariable int id, @Valid @RequestBody Quote newQuote) {
        try {
            service.updateQuote(id, newQuote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable int id) {
        try {
            service.deleteQuote(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
