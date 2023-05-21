package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.RecipeDto;
import com.asoft.spring6receipe.model.Category;
import com.asoft.spring6receipe.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeDto implements Converter<Recipe, RecipeDto> {

    private final CategoryToCategoryDto categoryConveter;
    private final IngredientToIngredientDto ingredientConverter;
    private final NotesToNotesDto notesConverter;

    public RecipeToRecipeDto(CategoryToCategoryDto categoryConveter, IngredientToIngredientDto ingredientConverter,
                                 NotesToNotesDto notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeDto convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(source.getId());
        recipeDto.setCookTime(source.getCookTime());
        recipeDto.setPrepTime(source.getPrepTime());
        recipeDto.setDescription(source.getDescription());
        recipeDto.setDifficulty(source.getDifficulty());
        recipeDto.setDirections(source.getDirections());
        recipeDto.setServings(source.getServings());
        recipeDto.setSource(source.getSource());
        recipeDto.setUrl(source.getUrl());
        recipeDto.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> recipeDto.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipeDto.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipeDto;
    }
}