package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerUnitTest {

    RecipeController recipeController;
    @Mock
    RecipeService recipeService;
    MockMvc mockMvc;

    Recipe sampleRecipe;

    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeController=new RecipeController(recipeService);
        mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        sampleRecipe=new Recipe();
        sampleRecipe.setId(1l);
    }
    @Test
    void showById() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(Optional.of(sampleRecipe));
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
                        .andReturn();
        Optional<Recipe> recipe=(Optional<Recipe>) result.getModelAndView().getModel().get("recipe");
        Recipe recipe_=recipe.orElse(null);
        assertEquals(1l,recipe_.getId());
        verify(recipeService,times(1)).findById(anyLong());

    }
}