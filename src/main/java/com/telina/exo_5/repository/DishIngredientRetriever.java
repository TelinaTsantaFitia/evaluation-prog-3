package com.telina.exo_5.repository;

import com.telina.exo_5.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DishIngredientRetriever {

    @Autowired
    private Connection connection;

    public List<Ingredient> findByDishWithFilters(int dishId, String name, Integer price) {

        List<Ingredient> list = new ArrayList<>();

        String sql = """
            SELECT i.*
            FROM ingredient i
            JOIN dish_ingredient di ON i.id = di.ingredient_id
            WHERE di.dish_id = ?
            AND (? IS NULL OR i.name ILIKE ?)
            AND (? IS NULL OR i.price BETWEEN ? AND ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, dishId);

            // 🔹 filtre NOM
            if (name != null) {
                ps.setString(2, name);
                ps.setString(3, "%" + name + "%");
            } else {
                ps.setNull(2, Types.VARCHAR);
                ps.setNull(3, Types.VARCHAR);
            }

            // 🔹 filtre PRIX
            if (price != null) {
                ps.setInt(4, price);
                ps.setInt(5, price - 50);
                ps.setInt(6, price + 50);
            } else {
                ps.setNull(4, Types.INTEGER);
                ps.setNull(5, Types.INTEGER);
                ps.setNull(6, Types.INTEGER);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Ingredient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("unit")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}