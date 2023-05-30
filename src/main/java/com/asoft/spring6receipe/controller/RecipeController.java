package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.exceptions.NotFoundException;
import com.asoft.spring6receipe.model.Recipe;
import com.asoft.spring6receipe.service.RecipeService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafView;

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView recipeErrorHandler(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("404error");
        return modelAndView;
    }
}
