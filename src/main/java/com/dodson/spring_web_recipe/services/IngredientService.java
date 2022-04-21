package com.dodson.spring_web_recipe.services;

import com.dodson.spring_web_recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}