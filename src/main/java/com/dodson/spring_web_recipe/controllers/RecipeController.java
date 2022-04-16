package com.dodson.spring_web_recipe.controllers;

import com.dodson.spring_web_recipe.services.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    
    @RequestMapping("/recipe/show/{recipeId}")
    public String showById(Model model, @PathVariable String recipeId) {
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(recipeId)));
        return "recipe/show";
    }
}
