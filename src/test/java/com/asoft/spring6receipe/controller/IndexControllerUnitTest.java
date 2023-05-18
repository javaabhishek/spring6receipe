package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerUnitTest {

    IndexController indexController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController=new IndexController(recipeService);
    }

    @Test
    void index() {
        Recipe recipe1=new Recipe();
        recipe1.setId(1l);
        Recipe recipe2=new Recipe();
        recipe2.setId(2l);
        Set<Recipe> recipeSet=new HashSet<>();
        recipeSet.add(recipe1);
        recipeSet.add(recipe2);

        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);

        when(recipeService.getAllRecipe()).thenReturn(recipeSet);
        String indexOutput=indexController.index(model);
        assertEquals(indexOutput,"index");
        verify(recipeService,times(1)).getAllRecipe();
        //verify(model,times(1)).addAttribute(eq("recipes"),anySet());
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> whatIsReturnByRecipeSerivce=argumentCaptor.getValue();
        assertEquals(2,whatIsReturnByRecipeSerivce.size());
    }
}