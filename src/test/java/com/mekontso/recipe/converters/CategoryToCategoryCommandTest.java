package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.CategoryCommand;
import com.mekontso.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    CategoryToCategoryCommand converter;

    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPTION = "DESCRIPTION";

    @BeforeEach
    void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    public void testEmptyObject()throws Exception{
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() throws Exception {
        //Given
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        //When
        CategoryCommand command = converter.convert(category);

        //Then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}