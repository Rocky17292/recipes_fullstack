package com.example.recipe_backend.controller;

import com.example.recipe_backend.entity.Recipe;
import com.example.recipe_backend.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/load")
    public ResponseEntity<String> loadData() {
        recipeService.loadRecipes();
        return ResponseEntity.ok("Recipes loaded successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> search(@RequestParam String query) {
        return ResponseEntity.ok(recipeService.searchRecipes(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }
}
