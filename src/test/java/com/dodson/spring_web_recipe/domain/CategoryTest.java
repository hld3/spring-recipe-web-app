package com.dodson.spring_web_recipe.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }
    
    @Test
    public void getId() {

        Long expectedId = 4L;
        category.setId(expectedId);

        assertEquals(expectedId, category.getId());
    }
}
