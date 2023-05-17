package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping({"","/","index","index.html"})
    public String index(Model model){
        log.info(">> Index controller <<");
        model.addAttribute("recipes",recipeService.getAllRecipe());
        return "index";
    }
}
