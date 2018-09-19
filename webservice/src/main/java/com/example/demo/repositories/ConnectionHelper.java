package com.example.demo.repositories;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
@PropertySource("classpath:application.properties")
public class ConnectionHelper {

    private String databaseUrl;
    private String username;
    private String password;

    @Autowired
    public ConnectionHelper(Environment environment){
        databaseUrl = environment.getProperty("database.url");
        username = environment.getProperty("database.username");
        password = environment.getProperty("database.password");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

}
