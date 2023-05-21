package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.IngredientDto;
import com.asoft.spring6receipe.dto.UnitOfMeasureDto;
import com.asoft.spring6receipe.model.Ingredient;
import com.asoft.spring6receipe.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class IngredientDtoToIngredientUnitTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = 1l;
    public static final Long UOM_ID = 2L;

    IngredientDtoToIngredient converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new IngredientDtoToIngredient(new UnitOfMeasureDtoToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        IngredientDto Dto = new IngredientDto();
        Dto.setId(ID_VALUE);
        Dto.setAmount(AMOUNT);
        Dto.setDescription(DESCRIPTION);
        UnitOfMeasureDto unitOfMeasureDto = new UnitOfMeasureDto();
        unitOfMeasureDto.setId(UOM_ID);
        Dto.setUnitOfMeasure(unitOfMeasureDto);

        //when
        Ingredient ingredient = converter.convert(Dto);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientDto Dto = new IngredientDto();
        Dto.setId(ID_VALUE);
        Dto.setAmount(AMOUNT);
        Dto.setDescription(DESCRIPTION);
        UnitOfMeasureDto unitOfMeasureDto = new UnitOfMeasureDto();


        //when
        Ingredient ingredient = converter.convert(Dto);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

    }

}