package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.IngredientCommand;
import com.mekontso.recipe.domain.Ingredient;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final  UnitOfMeasureToUnitOfMeasureCommand uomConverter;
    private final RecipeToRecipeCommand recipeConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter, RecipeToRecipeCommand recipeConverter) {
        this.uomConverter = uomConverter;
        this.recipeConverter = recipeConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null)
            return null;
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDrescription(ingredient.getDrescription());
        ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
       // ingredientCommand.setRecipe(recipeConverter.convert(ingredient.getRecipe()));
        return  ingredientCommand;
    }
}
