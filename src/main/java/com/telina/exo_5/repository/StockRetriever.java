package com.telina.exo_5.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class StockRetriever {

    @Autowired
    private Connection connection;

    public int getStockValue(int ingredientId, String at, String unit) {

        String sql = """
            SELECT COALESCE(SUM(quantity), 0) AS total
            FROM stock
            WHERE ingredient_id = ?
            AND date <= ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, ingredientId);
            ps.setString(2, at);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("total");

                // 🔥 conversion unité simple (exemple)
                if ("KG".equalsIgnoreCase(unit)) {
                    return stock / 1000;
                }

                return stock;
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}