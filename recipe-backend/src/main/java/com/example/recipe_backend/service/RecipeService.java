package com.example.recipe_backend.service;

import com.example.recipe_backend.entity.Recipe;

import java.util.List;

public interface RecipeService {
    void loadRecipes();
    List<Recipe> searchRecipes(String query);
    Recipe getRecipeById(Long id);
}
