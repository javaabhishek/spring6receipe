package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.dto.IngredientDto;


public interface IngredientService {
    IngredientDto findIngredientById(Long recipeId,Long ingredientId);

    IngredientDto saveIngredient(IngredientDto ingredient);
}
