package com.dodson.spring_web_recipe.bootstrap;

import java.math.BigDecimal;
import java.util.Set;

import com.dodson.spring_web_recipe.domain.Category;
import com.dodson.spring_web_recipe.domain.Ingredient;
import com.dodson.spring_web_recipe.domain.Notes;
import com.dodson.spring_web_recipe.domain.Recipe;
import com.dodson.spring_web_recipe.domain.UnitOfMeasure;
import com.dodson.spring_web_recipe.repositories.CategoryRepository;
import com.dodson.spring_web_recipe.repositories.RecipeRepository;
import com.dodson.spring_web_recipe.repositories.UnitOfMeasureRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RecipeDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;


    public RecipeDataLoader(UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadRecipes();
    }

    public void loadRecipes() {
        
        Recipe guacamole = new Recipe();

        // unit of measurements
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure tbsp = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure tsp = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure item = unitOfMeasureRepository.findByDescription("Item").get();

        // categories
        Category american = categoryRepository.findByDescription("American").get();
        Category mexican = categoryRepository.findByDescription("Mexican").get();

        // notes
        Notes guacNotes = new Notes();
        guacNotes.setRecipe(guacamole);
        guacNotes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");

        // guacamole ingredients
        Set<Ingredient> guacIngredients = guacamole.getIngredients();
        guacIngredients.add(new Ingredient("Avocado", new BigDecimal(2), item, guacamole));
        guacIngredients.add(new Ingredient("Salt", new BigDecimal(1.4), tsp, guacamole));
        guacIngredients.add(new Ingredient("Lime", new BigDecimal(1), tbsp, guacamole));
        guacIngredients.add(new Ingredient("Red Onion", new BigDecimal(2), tbsp, guacamole));
        guacIngredients.add(new Ingredient("Serrano", new BigDecimal(2), item, guacamole));
        guacIngredients.add(new Ingredient("Selantro", new BigDecimal(2), tbsp, guacamole));
        guacIngredients.add(new Ingredient("Black Pepper", new BigDecimal(1), pinch, guacamole));

        Set<Category> guacCategories = guacamole.getCategories();
        guacCategories.add(american);
        guacCategories.add(mexican);

        guacamole.setDescription("This is some guacamole that is supposedly perfect");
        guacamole.setPreptime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setSource("simplyrecipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("Cut the avocado. Mash the avocado flesh. Add remaining ingredients to taste. Serve.");
        guacamole.setNotes(guacNotes);

        recipeRepository.save(guacamole);
    }
}
