package com.dodson.spring_web_recipe.controllers;

import com.dodson.spring_web_recipe.commands.RecipeCommand;
import com.dodson.spring_web_recipe.exceptions.NotFoundException;
import com.dodson.spring_web_recipe.services.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_FORM_URL = "recipe/recipeForm";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    
    @GetMapping("/recipe/{id}/show")
    public String showById(Model model, @PathVariable String id) {
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return RECIPE_FORM_URL;
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Validated @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult result) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        if (result.hasErrors()) {
            log.debug("IN THE ERROR BLOCK!!!!!!!!!!!!!!!!!");
            result.getAllErrors().forEach(error -> {
                log.error(error.toString());
            });

            return RECIPE_FORM_URL;
        }

        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(Model model, @PathVariable String id) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));

        return RECIPE_FORM_URL;
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));

        log.debug("Deleting recipe, id: " + id);

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) // reused here to "throw" error since @ExceptionHandler takes precedence
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        var modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
