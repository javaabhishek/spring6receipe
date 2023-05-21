package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.CategoryDto;
import com.asoft.spring6receipe.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDtoToCategoryUnitTest {

    CategoryDtoToCategory categoryDtoToCategory;

    private static final Long CATEGORY_ID=1l;
    private static final String CATEGORY_DESCRIPTION="American";
    @BeforeEach
    void setUp() {
        categoryDtoToCategory=new CategoryDtoToCategory();
    }

    @Test
    void testNullModel() {
        assertNull(categoryDtoToCategory.convert(null));
    }

    @Test
    void testNotNullModel() {
        assertNotNull(categoryDtoToCategory.convert(new CategoryDto()));
    }

    @Test
    void convert() {

        //given
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(CATEGORY_ID);
        categoryDto.setDescription(CATEGORY_DESCRIPTION);

        //when
        Category categoryDomain= categoryDtoToCategory.convert(categoryDto);

        //then
        assertEquals(CATEGORY_ID,categoryDomain.getId());
        assertEquals(CATEGORY_DESCRIPTION,categoryDomain.getDescription());
    }
}