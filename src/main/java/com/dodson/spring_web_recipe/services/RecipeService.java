package com.dodson.spring_web_recipe.services;

import java.util.Set;

import com.dodson.spring_web_recipe.domain.Recipe;

public interface RecipeService {
    
    Set<Recipe> getRecipes();

    Recipe findById(long id);
}
