package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.IngredientCommand;
import com.mekontso.recipe.domain.Ingredient;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final  UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null)
            return null;
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        if(ingredient.getRecipe() != null){
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }
        return  ingredientCommand;
    }
}
