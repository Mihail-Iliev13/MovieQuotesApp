package com.example.demo;

import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.Quote;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
public class MainClass {

    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure()
                .addAnnotatedClass(Quote.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Character.class)
                .buildSessionFactory();
    }

}