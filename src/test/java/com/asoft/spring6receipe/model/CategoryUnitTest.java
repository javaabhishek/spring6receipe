package com.asoft.spring6receipe.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CategoryUnitTest {

    Category category;

    @BeforeEach
    void setUp() {
        category=new Category();
        category.setId(1l);
        category.setDescription("test");
    }

    @AfterEach
    void tearDown() {
        category=null;
    }

    @Test
    void getId() {
        assertEquals(1,category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}