
package org.example.shelflife.controllers;

import org.example.shelflife.entities.FoodItem;
import org.example.shelflife.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodRestController {

    @Autowired
    private FoodService service;

    @GetMapping
    public List<FoodItem> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<FoodItem> search(@RequestParam String name) {
        return service.findByName(name);
    }

    @PostMapping
    public FoodItem create(@RequestBody FoodItem item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> update(
            @PathVariable Long id,
            @RequestBody FoodItem updated
    ) {
        return service.findById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(service.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(f -> {
                    service.delete(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}