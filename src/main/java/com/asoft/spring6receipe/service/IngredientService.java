package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.dto.RecipeDto;


public interface IngredientService {
    IngredientDto findIngredientById(Long recipeId,Long ingredientId);

    IngredientDto saveIngredient(IngredientDto ingredient);

    RecipeDto deleteIngredient(Long recipeId, Long ingredientId);
}
