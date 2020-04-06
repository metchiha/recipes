package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.IngredientCommand;
import com.mekontso.recipe.commands.UnitOfMeasureCommand;
import com.mekontso.recipe.domain.Ingredient;
import com.mekontso.recipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    IngredientCommandToIngredient converter;

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM = new Long(2L);

    @BeforeEach
    void setUp() throws Exception{
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject()throws Exception{
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() throws Exception {
        //Given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(UOM);
        command.setUnitOfMeasure(uom);

        //When
        Ingredient ingredient = converter.convert(command);

        //Then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}