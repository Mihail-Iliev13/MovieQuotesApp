package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/quotes")
public class QuotesController {
private List<Quote> quotes= new ArrayList<>();
{
        quotes.add(new Quote(1,"I'll be back","The Terminator","Terminator",5));
}


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
        @PostMapping("api/quotes/new")
        public void CreateQuote(@RequestBody Quote newquote){
                quotes.add(newquote);
        }
        @PutMapping("/quotes/{id}")
        public void UpdateQuote(@RequestBody Quote newQuote, @PathVariable int id) {
                 Quote quote = getQuoteById(id);
                 int index = quotes.indexOf(quote);
                 quotes.set(index,newQuote);
         }

        @DeleteMapping("/quotes/{id}")
        void deleteEmployee(@PathVariable Long id) {
                quotes.remove(id);
        }

}
