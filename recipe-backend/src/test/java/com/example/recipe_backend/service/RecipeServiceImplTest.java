package com.example.recipe_backend.service;

import com.example.recipe_backend.entity.Recipe;
import com.example.recipe_backend.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setup() {
        recipeRepository = mock(RecipeRepository.class);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void testGetRecipeById() {
        Recipe dummy = new Recipe(1L, "Burger", "American", "img", List.of("Bun", "Meat"));
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(dummy));

        Recipe result = recipeService.getRecipeById(1L);

        assertEquals("Burger", result.getName());
        verify(recipeRepository).findById(1L);
    }

    @Test
    void testSearchRecipes() {
        Recipe one = new Recipe(1L, "Paneer Butter Masala", "Indian", "", List.of());
        Recipe two = new Recipe(2L, "Sushi", "Japanese", "", List.of());
        when(recipeRepository.findAll()).thenReturn(List.of(one, two));

        List<Recipe> results = recipeService.searchRecipes("paneer");

        assertEquals(1, results.size());
        assertEquals("Paneer Butter Masala", results.get(0).getName());
    }
}
