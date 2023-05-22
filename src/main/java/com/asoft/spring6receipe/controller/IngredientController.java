package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.converter.IngredientDtoToIngredient;
import com.asoft.spring6receipe.converter.IngredientToIngredientDto;
import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.dto.UnitOfMeasureDto;
import com.asoft.spring6receipe.service.IngredientService;
import com.asoft.spring6receipe.service.RecipeService;
import com.asoft.spring6receipe.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final IngredientToIngredientDto ingredientToIngredientDto;
    private final IngredientDtoToIngredient ingredientDtoToIngredient;
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService, IngredientToIngredientDto ingredientToIngredientDto, IngredientDtoToIngredient ingredientDtoToIngredient) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId,@PathVariable Long ingredientId,Model model){
        IngredientDto ingredientDto= ingredientService.findIngredientById(recipeId,ingredientId);
        model.addAttribute("ingredient",ingredientDto);
        return "recipe/ingredients/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable Long recipeId,@PathVariable Long ingredientId,Model model){
        IngredientDto ingredientDto=ingredientService.findIngredientById(recipeId,ingredientId);
        Set<UnitOfMeasureDto> unitOfMeasureDtoSet=unitOfMeasureService.listAllUoms();
        model.addAttribute("ingredient",ingredientDto);
        model.addAttribute("uomList",unitOfMeasureDtoSet);
        return "/recipe/ingredients/ingredientform";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String updateRecipeIngredient(@ModelAttribute IngredientDto ingredientDto){
        IngredientDto ingredient=ingredientService.saveIngredient(ingredientDto);
        return "redirect:/ingredient/recipe/"+ingredient.getRecipeId()+"/ingredient/"+ingredient.getId()+"/show";
    }
}
