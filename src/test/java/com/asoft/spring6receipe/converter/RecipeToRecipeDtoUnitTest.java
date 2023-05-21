package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.enumeration.Difficulty;
import com.asoft.spring6receipe.model.Category;
import com.asoft.spring6receipe.model.Ingredient;
import com.asoft.spring6receipe.model.Notes;
import com.asoft.spring6receipe.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeToRecipeDtoUnitTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    RecipeToRecipeDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new RecipeToRecipeDto(
                new CategoryToCategoryDto(),
                new IngredientToIngredientDto(new UnitOfMeasureToUnitOfMeasureDto()),
                new NotesToNotesDto());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeDto Dto = converter.convert(recipe);

        //then
        assertNotNull(Dto);
        assertEquals(RECIPE_ID, Dto.getId());
        assertEquals(COOK_TIME, Dto.getCookTime());
        assertEquals(PREP_TIME, Dto.getPrepTime());
        assertEquals(DESCRIPTION, Dto.getDescription());
        assertEquals(DIFFICULTY, Dto.getDifficulty());
        assertEquals(DIRECTIONS, Dto.getDirections());
        assertEquals(SERVINGS, Dto.getServings());
        assertEquals(SOURCE, Dto.getSource());
        assertEquals(URL, Dto.getUrl());
        assertEquals(NOTES_ID, Dto.getNotes().getId());
        assertEquals(2, Dto.getCategories().size());
        assertEquals(2, Dto.getIngredients().size());

    }

}