package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/show/{recipeId}")
    public String showById(@PathVariable Long recipeId, Model model){
        Recipe recipe= recipeService.findById(recipeId).get();
        log.info("recipe:"+recipe.getDescription());
        model.addAttribute("recipe",recipe);
        return "recipe/show";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeDto());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("/createupdate")
    public String createOrUpdate(@ModelAttribute RecipeDto recipeDto){
        RecipeDto savedRecipeDto=recipeService.saveRecipe(recipeDto);
        return "redirect:/recipe/show/"+savedRecipeDto.getId();
    }
}
