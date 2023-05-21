package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.converter.RecipeDtoToRecipe;
import com.asoft.spring6receipe.converter.RecipeToRecipeDto;
import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    private final RecipeDtoToRecipe recipeDtoToRecipe;
    private final RecipeToRecipeDto recipeToRecipeDto;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeDtoToRecipe recipeDtoToRecipe, RecipeToRecipeDto recipeToRecipeDto) {
        this.recipeRepository = recipeRepository;
        this.recipeDtoToRecipe = recipeDtoToRecipe;
        this.recipeToRecipeDto = recipeToRecipeDto;
    }

    @Override
    public Set<Recipe> getAllRecipe() {
        Set<Recipe> recipes=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Optional<Recipe> findById(Long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    @Override
    @Transactional
    public RecipeDto saveRecipe(RecipeDto recipeDto) {
        Recipe detachedRecipe=recipeDtoToRecipe.convert(recipeDto);
        Recipe recipe= recipeRepository.save(detachedRecipe);
        return recipeToRecipeDto.convert(recipe);
    }
}
