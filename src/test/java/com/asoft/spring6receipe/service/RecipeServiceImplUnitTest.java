package com.asoft.spring6receipe.service;

import com.asoft.spring6receipe.converter.RecipeDtoToRecipe;
import com.asoft.spring6receipe.converter.RecipeToRecipeDto;
import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplUnitTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeDto recipeToRecipeDto;
    @Mock
    RecipeDtoToRecipe recipeDtoToRecipe;

    Recipe sampleRecipe;
    Set<Recipe> recipeSet=new HashSet<>();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository, recipeDtoToRecipe, recipeToRecipeDto);
        sampleRecipe=new Recipe();
        sampleRecipe.setId(1l);
        recipeSet.add(sampleRecipe);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllRecipe() {
        when(recipeRepository.findAll()).thenReturn(recipeSet);//Imp
        assertEquals(1,recipeService.getAllRecipe().size());
        verify(recipeRepository,times(1)).findAll();// here we are confirming
        //that mocked recipeRepository findAll method executed only once
    }

    @Test
    void findById() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(sampleRecipe));
        Optional<Recipe> recipe= recipeService.findById(1l);
        assertEquals(1l,recipe.get().getId());
    }

    @Test
    void saveRecipe() {
        when(recipeDtoToRecipe.convert(any())).thenReturn(sampleRecipe);
        when(recipeRepository.save(any())).thenReturn(sampleRecipe);
        RecipeDto recipeDto=new RecipeDto();
        recipeDto.setId(1l);
        when(recipeToRecipeDto.convert(any())).thenReturn(recipeDto);
        RecipeDto savedDto= recipeService.saveRecipe(recipeDto);
        assertEquals(1l,savedDto.getId());

        verify(recipeRepository,times(1)).save(any());
        verify(recipeDtoToRecipe,times(1)).convert(any());
        verify(recipeToRecipeDto,times(1)).convert(any());
    }
}