package com.telina.exo_5.controller;

import com.telina.exo_5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getStockValue(
            @PathVariable int id,
            @RequestParam(required = false) String at,
            @RequestParam(required = false) String unit) {

        try {
            int value = stockService.calculateStockValue(id, at, unit);
            return ResponseEntity.ok(value);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}