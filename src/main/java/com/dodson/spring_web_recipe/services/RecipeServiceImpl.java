package com.dodson.spring_web_recipe.services;

import java.util.HashSet;
import java.util.Set;

import com.dodson.spring_web_recipe.domain.Recipe;
import com.dodson.spring_web_recipe.repositories.RecipeRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    
    private RecipeRepository recipeRepository;


    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
        return rOptional.orElse(null);
    }
}
