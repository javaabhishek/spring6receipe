package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.model.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipe();

    Optional<Recipe> findById(Long recipeId);
}
