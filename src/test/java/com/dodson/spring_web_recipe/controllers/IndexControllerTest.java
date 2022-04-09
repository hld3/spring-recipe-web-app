package com.dodson.spring_web_recipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import com.dodson.spring_web_recipe.domain.Recipe;
import com.dodson.spring_web_recipe.services.RecipeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class IndexControllerTest {
    
    @Mock
    Model model;

    @Mock
    RecipeService recipeService;

    IndexController indexController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> mockRecipes = Collections.singleton(new Recipe());
        when(recipeService.getRecipes()).thenReturn(mockRecipes);

        assertEquals("index", indexController.getIndexPage(model));
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute("recipes", mockRecipes);
    }
}
