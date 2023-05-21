package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.CategoryDto;
import com.asoft.spring6receipe.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryDtoUnitTest {

    CategoryToCategoryDto categoryToCategoryDto;

    @BeforeEach
    void setUp() {
        categoryToCategoryDto=new CategoryToCategoryDto();
    }

    @Test
    void testNullDto() {
        assertNull(categoryToCategoryDto.convert(null));
    }

    @Test
    void testNotNullAndEmptyDto() {
        assertNotNull(categoryToCategoryDto.convert(new Category()));
    }

    @Test
    void convert() {
        //given
        Category category=new Category();
        category.setId(1l);
        category.setDescription("American");

        //when
        CategoryDto categoryDto=categoryToCategoryDto.convert(category);
        assertEquals(1l,categoryDto.getId());
        assertEquals("American",categoryDto.getDescription());
    }
}