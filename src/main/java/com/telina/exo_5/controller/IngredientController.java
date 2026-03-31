package com.telina.exo_5.controller;

import com.telina.exo_5.entity.Ingredient;
import com.telina.exo_5.service.IngredientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService service;

    @GetMapping
    public List<Ingredient> getAll() {
        return service.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Ingredient ingredient = service.getIngredientById(id);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}