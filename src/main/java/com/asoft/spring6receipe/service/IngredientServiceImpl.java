package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.converter.IngredientToIngredientDto;
import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.model.Ingredient;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientDto ingredientToIngredientDto;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientDto ingredientToIngredientDto) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientDto = ingredientToIngredientDto;
    }

    @Override
    public IngredientDto findIngredientById(Long recipeId,Long ingredientId) {
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(() ->new RuntimeException("Recipe Not found"));
        Optional<Ingredient> ingredient= recipe.getIngredients().stream().filter(ing -> ing.getId().equals(ingredientId))
                .findFirst();
        return ingredientToIngredientDto.convert(ingredient.orElseThrow(()->new RuntimeException("Ingredient not found")));
    }
}
