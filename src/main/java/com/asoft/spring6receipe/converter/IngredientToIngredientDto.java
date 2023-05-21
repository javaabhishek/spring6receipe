package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientDto implements Converter<Ingredient, IngredientDto> {

    private final UnitOfMeasureToUnitOfMeasureDto uomConverter;

    public IngredientToIngredientDto(UnitOfMeasureToUnitOfMeasureDto uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientDto convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setAmount(ingredient.getAmount());
        ingredientDto.setDescription(ingredient.getDescription());
        ingredientDto.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        return ingredientDto;
    }
}