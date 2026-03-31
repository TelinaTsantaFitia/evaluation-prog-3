package com.telina.exo_5.controller;

import com.telina.exo_5.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService service;

    // GET /dishes
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllDishes());
    }

    // GET /dishes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getDish(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.getDish(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // 🔥 GET filtré
    @GetMapping("/{id}/ingredients")
    public ResponseEntity<?> getIngredients(
            @PathVariable int id,
            @RequestParam(required = false) String ingredientName,
            @RequestParam(required = false) Integer ingredientPriceAround) {

        try {
            return ResponseEntity.ok(
                    service.getIngredients(id, ingredientName, ingredientPriceAround)
            );
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // 🔥 PUT
    @PutMapping("/{id}/ingredients")
    public ResponseEntity<?> updateIngredients(
            @PathVariable int id,
            @RequestBody List<Integer> ingredientIds) {

        try {
            service.updateDishIngredients(id, ingredientIds);
            return ResponseEntity.ok("Updated");

        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}