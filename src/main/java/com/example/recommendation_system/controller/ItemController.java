package com.example.recommendation.controller;

import com.example.recommendation.model.Item;
import com.example.recommendation.model.dto.ItemDTO;
import com.example.recommendation.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(itemService.createItem(itemDTO));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(itemService.getItemsByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @Valid @RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(itemService.updateItem(id, itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}