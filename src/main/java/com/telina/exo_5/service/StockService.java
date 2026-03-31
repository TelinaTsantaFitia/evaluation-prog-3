package com.telina.exo_5.service;

import com.telina.exo_5.repository.IngredientRetriever;
import com.telina.exo_5.repository.StockRetriever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRetriever stockRetriever;

    @Autowired
    private IngredientRetriever ingredientRetriever;

    public int calculateStockValue(int id, String at, String unit) {

        if (at == null || unit == null) {
            throw new IllegalArgumentException(
                    "Either query parameter 'at' or 'unit' is not provided"
            );
        }

        if (ingredientRetriever.findById(id) == null) {
            throw new RuntimeException("Ingredient.id=" + id + " is not found");
        }

        return stockRetriever.getStockValue(id, at, unit);
    }
}