package com.asoft.spring6receipe.bootstrap;

import com.asoft.spring6receipe.enumeration.Difficulty;
import com.asoft.spring6receipe.model.*;
import com.asoft.spring6receipe.repositories.CategoryRepository;
import com.asoft.spring6receipe.repositories.RecipeRepository;
import com.asoft.spring6receipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Component
@Slf4j
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootStrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    public List<Recipe> loadData() {
        List<Recipe> allRecipe=new ArrayList<>();
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");//description
        guacRecipe.setPrepTime(10);//prepTime
        guacRecipe.setCookTime(0);//cookTime
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);
        guacNotes.setRecipe(guacRecipe);

        UnitOfMeasure eachUom=unitOfMeasureRepository.findByUom("Each").get();
        UnitOfMeasure teaspoonUom=unitOfMeasureRepository.findByUom("Teaspoon").get();
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), guacRecipe,eachUom));
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), guacRecipe,teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), guacRecipe,eachUom));
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), guacRecipe,teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), guacRecipe,eachUom));

        Category american=categoryRepository.findByDescription("American").get();
        guacRecipe.getCategories().add(american);
        allRecipe.add(guacRecipe);

        return allRecipe;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(loadData());
        log.info("Guac recipe is saved...");
    }
}
