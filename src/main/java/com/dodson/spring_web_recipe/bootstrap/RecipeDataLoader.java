package com.dodson.spring_web_recipe.bootstrap;

import java.math.BigDecimal;
import java.util.Set;

import com.dodson.spring_web_recipe.domain.Category;
import com.dodson.spring_web_recipe.domain.Difficulty;
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

        // unit of measurements
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure tbsp = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure tsp = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure item = unitOfMeasureRepository.findByDescription("Item").get();
        UnitOfMeasure lbs = unitOfMeasureRepository.findByDescription("Pounds").get();

        // categories
        Category american = categoryRepository.findByDescription("American").get();
        Category mexican = categoryRepository.findByDescription("Mexican").get();

        // Guacamole
        Recipe guacamole = new Recipe();

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

        guacamole.setDescription("Guacamole");
        guacamole.setPreptime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setSource("simplyrecipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("Cut the avocado. Mash the avocado flesh. Add remaining ingredients to taste. Serve.");
        guacamole.setNotes(guacNotes);
        guacamole.setDifficulty(Difficulty.EASY);

        recipeRepository.save(guacamole);

        // Chicken Tacos
        Recipe chickenTacos = new Recipe();

        // notes
        Notes chickenTacoNotes = new Notes();
        chickenTacoNotes.setRecipe(chickenTacos);
        chickenTacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.");

        // chicken taco ingredients
        Set<Ingredient> tacoIngredients = chickenTacos.getIngredients();
        tacoIngredients.add(new Ingredient("Chili Powder", new BigDecimal(2), tbsp, chickenTacos));
        tacoIngredients.add(new Ingredient("Oregano", new BigDecimal(1), tsp, chickenTacos));
        tacoIngredients.add(new Ingredient("Cumin", new BigDecimal(1), tsp, chickenTacos));
        tacoIngredients.add(new Ingredient("Sugar", new BigDecimal(1), tsp, chickenTacos));
        tacoIngredients.add(new Ingredient("Salt", new BigDecimal(.5), tsp, chickenTacos));
        tacoIngredients.add(new Ingredient("Garlic Clove", new BigDecimal(1), item, chickenTacos));
        tacoIngredients.add(new Ingredient("Orange Zest", new BigDecimal(1), tbsp, chickenTacos));
        tacoIngredients.add(new Ingredient("Boneless Chicken Thighs", new BigDecimal(1.25), lbs, chickenTacos));

        Set<Category> tacoCategories = chickenTacos.getCategories();
        tacoCategories.add(american);
        tacoCategories.add(mexican);

        chickenTacos.setDescription("Chicken Tacos");
        chickenTacos.setPreptime(20);
        chickenTacos.setCookTime(15);
        chickenTacos.setServings(6);
        chickenTacos.setSource("simplyrecipes");
        chickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTacos.setDirections("First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings. Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");
        chickenTacos.setNotes(chickenTacoNotes);
        chickenTacos.setDifficulty(Difficulty.MODERATE);

        recipeRepository.save(chickenTacos);
    }
}
