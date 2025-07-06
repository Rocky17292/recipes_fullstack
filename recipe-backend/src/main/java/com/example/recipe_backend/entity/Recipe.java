package com.example.recipe_backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    private Long id;

    private String name;

    private String cuisine;

    private String image;

    @ElementCollection
    private List<String> ingredients;

    public Recipe() {
    }

    public Recipe(Long id, String name, String cuisine, String image, List<String> ingredients) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.image = image;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
