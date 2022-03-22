package com.dodson.spring_web_recipe.controllers;

import java.util.Optional;

import com.dodson.spring_web_recipe.domain.Category;
import com.dodson.spring_web_recipe.domain.UnitOfMeasure;
import com.dodson.spring_web_recipe.repositories.CategoryRepository;
import com.dodson.spring_web_recipe.repositories.UnitOfMeasureRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {
        Optional<Category> aCategory = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> aUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println(aCategory.get().getDescription());
        System.out.println(aUnitOfMeasure.get().getId());

        return "index";
    }
}