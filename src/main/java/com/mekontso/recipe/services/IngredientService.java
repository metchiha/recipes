package com.mekontso.recipe.services;

import com.mekontso.recipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long idRecipe, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long ingredientId);
}
