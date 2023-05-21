package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientDtoToIngredient implements Converter<IngredientDto, Ingredient> {

    private final UnitOfMeasureDtoToUnitOfMeasure uomConverter;

    public IngredientDtoToIngredient(UnitOfMeasureDtoToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientDto source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}