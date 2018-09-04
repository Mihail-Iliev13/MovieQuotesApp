package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/quotes")
public class QuotesController {
private List<Quote> quotes= Arrays.asList(
        new Quote(1,"I'll be back","The Terminator","Terminator",5)
);

@GetMapping("api/quotes/all")
public List<Quote> getQuotes()
{
        return quotes;
}
@GetMapping("api/quotes/{id}")
public Quote getQuoteById(@PathVariable int id){
return quotes
        .stream()
        .filter(x->x.getId()==id)
        .findFirst()
        .orElse(null);
        }
}
