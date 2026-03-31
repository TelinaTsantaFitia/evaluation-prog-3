package com.telina.exo_5.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class DishWriter {

    @Autowired
    private Connection connection;

    public void replaceIngredients(int dishId, List<Integer> ingredientIds) {

        try {
            // supprimer anciens
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM dish_ingredient WHERE dish_id = ?"
            );
            delete.setInt(1, dishId);
            delete.executeUpdate();

            // ajouter nouveaux
            for (Integer id : ingredientIds) {
                PreparedStatement insert = connection.prepareStatement(
                        "INSERT INTO dish_ingredient(dish_id, ingredient_id) VALUES (?, ?)"
                );
                insert.setInt(1, dishId);
                insert.setInt(2, id);
                insert.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}