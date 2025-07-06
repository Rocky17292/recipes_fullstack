package com.example.recipe_backend.controller;

import com.example.recipe_backend.entity.Recipe;
import com.example.recipe_backend.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    void testLoadRecipes() throws Exception {
        mockMvc.perform(post("/recipes/load"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchRecipes() throws Exception {
        Recipe dummy = new Recipe(1L, "Pizza", "Italian", "img.jpg", List.of("Cheese", "Dough"));
        Mockito.when(recipeService.searchRecipes("piz")).thenReturn(List.of(dummy));

        mockMvc.perform(get("/recipes/search").param("query", "piz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"));
    }

    @Test
    void testGetRecipeById() throws Exception {
        Recipe dummy = new Recipe(1L, "Pizza", "Italian", "img.jpg", List.of("Cheese", "Dough"));
        Mockito.when(recipeService.getRecipeById(1L)).thenReturn(dummy);

        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pizza"));
    }
}
