package com.telina.exo_5.service;

import com.telina.exo_5.entity.Dish;
import com.telina.exo_5.entity.Ingredient;
import com.telina.exo_5.repository.DishIngredientRetriever;
import com.telina.exo_5.repository.DishRetriever;
import com.telina.exo_5.repository.DishWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishIngredientRetriever ingredientRetriever;

    @Autowired
    private DishRetriever retriever;

    @Autowired
    private DishWriter writer;

    public List<Dish> getAllDishes() {
        return retriever.findAll();
    }

    // GET /dishes/{id}
    public Dish getDish(int id) {
        Dish dish = retriever.findById(id);

        if (dish == null) {
            throw new RuntimeException("Dish.id=" + id + " is not found");
        }

        return dish;
    }

    public List<Ingredient> getIngredients(int id, String name, Integer price) {

        if (retriever.findById(id) == null) {
            throw new RuntimeException("Dish.id=" + id + " is not found");
        }

        return ingredientRetriever.findByDishWithFilters(id, name, price);
    }

    public void updateDishIngredients(int dishId, List<Integer> ids) {

        if (retriever.findById(dishId) == null) {
            throw new RuntimeException("Dish.id=" + dishId + " is not found");
        }

        writer.replaceIngredients(dishId, ids);
    }
}