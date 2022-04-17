package com.dodson.spring_web_recipe.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import com.dodson.spring_web_recipe.converters.RecipeCommandToRecipe;
import com.dodson.spring_web_recipe.converters.RecipeToRecipeCommand;
import com.dodson.spring_web_recipe.domain.Recipe;
import com.dodson.spring_web_recipe.repositories.RecipeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }
    
    @Test
    public void getRecipes() {
        Set<Recipe> mockRecipes = Collections.singleton(new Recipe());
        when(recipeRepository.findAll()).thenReturn(mockRecipes);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipesById() {

        var expectedId = 1l;
        var recipe = new Recipe();
        recipe.setId(expectedId) ;
        var rOptional = Optional.of(recipe);

        when(recipeRepository.findById(expectedId)).thenReturn(rOptional);

        var recipeReturned = recipeService.findById(expectedId);

        assertNotNull(recipeReturned, "Null recipe returned");
        verify(recipeRepository, times(1)).findById(expectedId);
        verify(recipeRepository, never()).findAll();
    }
}
