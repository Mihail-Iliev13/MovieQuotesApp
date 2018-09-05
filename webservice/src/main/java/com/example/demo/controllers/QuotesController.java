package com.example.demo.controllers;

import com.example.demo.models.Quote;
import com.example.demo.service.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/quotes")
public class QuotesController {
private QuotesService service;

@Autowired
public QuotesController(QuotesService service) {
        this.service = service;
}

@GetMapping("/all")
public List<Quote> getQuotes()
{
return service.getQuotes();
}

@GetMapping("id/{id}")
public Quote getQuoteById(@PathVariable int id){
return service.getQuoteById(id);
        }

@PostMapping("/new")
public void createQuote(@RequestBody Quote newQuote){
        service.createQuote(newQuote);
}

@PutMapping("update/{id}")
public void updateQuote( @PathVariable int id,@RequestBody Quote newQuote) {
        service.updateQuote(id,newQuote);
}

@DeleteMapping("delete/{id}")
public void deleteQuote(@PathVariable int id) {
service.deleteQuote(id);
        }
}
