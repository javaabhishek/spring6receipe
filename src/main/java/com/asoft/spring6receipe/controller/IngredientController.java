package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.converter.IngredientDtoToIngredient;
import com.asoft.spring6receipe.converter.IngredientToIngredientDto;
import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.service.IngredientService;
import com.asoft.spring6receipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final IngredientToIngredientDto ingredientToIngredientDto;
    private final IngredientDtoToIngredient ingredientDtoToIngredient;
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, IngredientToIngredientDto ingredientToIngredientDto, IngredientDtoToIngredient ingredientDtoToIngredient) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.ingredientToIngredientDto = ingredientToIngredientDto;
        this.ingredientDtoToIngredient = ingredientDtoToIngredient;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable long recipeId, Model model){
        RecipeDto recipeDto=recipeService.findRecipeById(recipeId);
        model.addAttribute("recipe",recipeDto);
        return "/recipe/ingredients/list";
    }

    ///recipe/' + ${recipe.id} + '/ingredient/' + ${ingredient.id} + '/show
    //ingredient/recipe/1/ingredient/1/show
    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId,@PathVariable Long ingredientId,Model model){
        IngredientDto ingredientDto= ingredientService.findIngredientById(recipeId,ingredientId);
        model.addAttribute("ingredient",ingredientDto);
        return "recipe/ingredients/show";
    }
}
