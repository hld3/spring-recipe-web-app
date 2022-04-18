package com.dodson.spring_web_recipe.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import com.dodson.spring_web_recipe.commands.RecipeCommand;
import com.dodson.spring_web_recipe.converters.RecipeCommandToRecipe;
import com.dodson.spring_web_recipe.converters.RecipeToRecipeCommand;
import com.dodson.spring_web_recipe.domain.Recipe;
import com.dodson.spring_web_recipe.repositories.RecipeRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }    

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Retrieving all recipes");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return (Set<Recipe>) recipes;
    }

    public Recipe findById(long id) {

        var rOptional = recipeRepository.findById(id);

        if(!rOptional.isPresent()) {
            throw new RuntimeException("No recipe found with id: " + id);
        }
        return rOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipe of id: " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }
}
