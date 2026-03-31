package com.telina.exo_5.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.DriverManager;

@Configuration
public class DatabaseConnection {

    @Bean
    public java.sql.Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/ingredient",
                    "postgres",
                    "telina123"
            );
        } catch (Exception e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }
}
