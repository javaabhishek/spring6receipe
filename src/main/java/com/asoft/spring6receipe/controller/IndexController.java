package com.asoft.spring6receipe.controller;

import com.asoft.spring6receipe.repositories.CategoryRepository;
import com.asoft.spring6receipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping({"","/","index","index.html"})
    public String index(){
        System.out.println(categoryRepository.findByDescription("American").get().getDescription());
        System.out.println(unitOfMeasureRepository.findByUom("Teaspoon").get().getUom());
        return "index";
    }
}
