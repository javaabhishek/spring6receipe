package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.converter.IngredientDtoToIngredient;
import com.asoft.spring6receipe.converter.IngredientToIngredientDto;
import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.model.Ingredient;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.repositories.RecipeRepository;
import com.asoft.spring6receipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final IngredientToIngredientDto ingredientToIngredientDto;
    private final IngredientDtoToIngredient ingredientDtoToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientToIngredientDto ingredientToIngredientDto,
                                 IngredientDtoToIngredient ingredientDtoToIngredient) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientDto = ingredientToIngredientDto;
        this.ingredientDtoToIngredient = ingredientDtoToIngredient;
    }

    @Override
    public IngredientDto findIngredientById(Long recipeId,Long ingredientId) {
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(() ->
                new RuntimeException("Recipe Not found"));
        Optional<Ingredient> ingredient= recipe.getIngredients().stream()
                .filter(ing -> ing.getId().equals(ingredientId))
                .findFirst();
        return ingredientToIngredientDto.convert(ingredient.orElseThrow(()->
                new RuntimeException("Ingredient not found")));
    }

    @Override
    public IngredientDto saveIngredient(IngredientDto ingredient) {
        Recipe recipe=recipeRepository.findById(ingredient.getRecipeId()).orElseThrow(()->
                new RuntimeException("Recipe not found"));

        Optional<Ingredient> ingredientFromDbOptional= recipe.getIngredients().stream().filter(ing->ing.getId()
                        .equals(ingredient.getId()))
                .findFirst();
        //now update the ingredientFromDb object from IngredientDto ingredient and save the recipe object.

        if(ingredientFromDbOptional.isPresent()){
            Ingredient ingredientFromDb=ingredientFromDbOptional.get();
            ingredientFromDb.setDescription(ingredient.getDescription());
            ingredientFromDb.setAmount(ingredient.getAmount());
            ingredientFromDb.setUnitOfMeasure(unitOfMeasureRepository.findById(ingredient.getUnitOfMeasure()
                    .getId()).orElseThrow(()-> new RuntimeException("UOM not found")));
        }else{
            recipe.addIngredient(ingredientDtoToIngredient.convert(ingredient));
        }

        Recipe savedRecipe=recipeRepository.save(recipe);

        //below statement may fail in case of new ingredient add
        return ingredientToIngredientDto.convert(savedRecipe.getIngredients().stream()
                .filter(ing->ing.getId().equals(ingredient.getId())).findFirst().get());
    }
}
