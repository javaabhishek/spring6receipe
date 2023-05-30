package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.service.RecipeService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/show/{recipeId}")
    public String showById(@PathVariable Long recipeId, Model model){
        Recipe recipe= recipeService.findById(recipeId);
        log.info("recipe:"+recipe.getDescription());
        model.addAttribute("recipe",recipe);
        return "recipe/show";
    }

    @GetMapping("/update/{recipeId}")
    public String update(@PathVariable Long recipeId,Model model){
        RecipeDto recipeDto=recipeService.findRecipeById(recipeId);
        model.addAttribute("recipe",recipeDto);
        return "recipe/recipeform";
    }

    @GetMapping("/delete/{recipeId}")
    public String delete(@PathVariable Long recipeId){
        recipeService.deleteById(recipeId);
        return "redirect:/index";
    }

    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeDto());
        return "recipe/recipeform";
    }


    @PostMapping("/createupdate")
    public String createOrUpdate(@ModelAttribute RecipeDto recipeDto){
        RecipeDto savedRecipeDto=recipeService.saveRecipe(recipeDto);
        return "redirect:/recipe/show/"+savedRecipeDto.getId();
    }
}
