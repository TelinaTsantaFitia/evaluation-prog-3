package com.telina.exo_5.repository;

import com.telina.exo_5.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredientRetriever {

    @Autowired
    private Connection connection;

    public List<Ingredient> findAll() {

        List<Ingredient> list = new ArrayList<>();

        String sql = "SELECT * FROM ingredient";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

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

    public Ingredient findById(int id) {

        String sql = "SELECT * FROM ingredient WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Ingredient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("unit")
                );
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Erreur récupération ingrédient", e);
        }
    }
}