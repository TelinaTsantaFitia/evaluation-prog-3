package com.telina.exo_5.service;

import com.telina.exo_5.entity.Ingredient;
import com.telina.exo_5.repository.IngredientRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRetriever retriever;

    public List<Ingredient> getAllIngredients() {
        return retriever.findAll();
    }

    public Ingredient getIngredientById(int id) {

        Ingredient ingredient = retriever.findById(id);

        if (ingredient == null) {
            throw new RuntimeException("Ingredient.id=" + id + " is not found");
        }

        return ingredient;
    }
}