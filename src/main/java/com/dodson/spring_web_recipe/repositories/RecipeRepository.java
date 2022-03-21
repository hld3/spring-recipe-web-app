package com.dodson.spring_web_recipe.repositories;

import com.dodson.spring_web_recipe.domain.Recipe;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    
}
