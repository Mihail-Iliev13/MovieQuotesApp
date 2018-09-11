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
        return service.getQuotes();
    }

    @GetMapping("/{id}")
    public Quote getQuoteById(@PathVariable int id) {
        return service.getQuoteById(id);
    }

    @PostMapping
    public void createQuote(@Valid @RequestBody Quote newQuote) {
        service.createQuote(newQuote);
    }

    @PutMapping("/{id}")
    public void updateQuote(@PathVariable int id, @Valid @RequestBody Quote newQuote) {
        service.updateQuote(id, newQuote);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable int id) {
        service.deleteQuote(id);
    }
}
