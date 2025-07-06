package com.example.recipe_backend.service;

import com.example.recipe_backend.entity.Recipe;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import com.example.recipe_backend.repository.RecipeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Retryable(
    	    value = { Exception.class },
    	    maxAttempts = 3,
    	    backoff = @Backoff(delay = 2000)
    	)
    @Override
    public void loadRecipes() {
        String url = "https://dummyjson.com/recipes"; // change this url to test the retry logic
        System.out.println("Calling external API...");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> recipes = (List<Map<String, Object>>) response.get("recipes");

        List<Recipe> recipeList = new ArrayList<>();

        for (Map<String, Object> item : recipes) {
            Long id = Long.valueOf(item.get("id").toString());
            String name = (String) item.get("name");
            String cuisine = (String) item.get("cuisine");
            String image = (String) item.get("image");

            List<String> ingredients = (List<String>) item.get("ingredients");

            Recipe recipe = new Recipe(id, name, cuisine, image, ingredients);
            recipeList.add(recipe);
        }

        recipeRepository.saveAll(recipeList);
    }

    @Override
    public List<Recipe> searchRecipes(String query) {
        // Simple contains filter (replace with Hibernate Search later)
        return recipeRepository.findAll().stream()
                .filter(r -> r.getName().toLowerCase().contains(query.toLowerCase())
                          || r.getCuisine().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + id));
    }
}
