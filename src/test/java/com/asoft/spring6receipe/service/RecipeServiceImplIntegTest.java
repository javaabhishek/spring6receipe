package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.converter.RecipeDtoToRecipe;
import com.asoft.spring6receipe.converter.RecipeToRecipeDto;
import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeServiceImplIntegTest {
    public static final String NEW_DESCRIPTION = "New Description";
    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeDto recipeToRecipeDto;

    @Autowired
    RecipeDtoToRecipe recipeDtoToRecipe;

    @Test
    @Transactional
    void testSaveRecipe() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeDto testRecipeCommand = recipeToRecipeDto.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeDto savedRecipeCommand = recipeService.saveRecipe(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}