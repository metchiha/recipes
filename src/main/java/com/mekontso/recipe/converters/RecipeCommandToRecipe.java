package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.RecipeCommand;
import com.mekontso.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NoteCommandToNote notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(@Lazy NoteCommandToNote notesConverter,
                                 @Lazy IngredientCommandToIngredient ingredientConverter,
                                 @Lazy CategoryCommandToCategory categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null)
            return null;
        final Recipe recipe = new Recipe();
        recipe.setSource(recipeCommand.getSource());
        recipe.setServing(recipeCommand.getServing());
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime((recipeCommand.getPrepTime()));
        recipe.setImage(recipeCommand.getImage());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));
        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients()
                    .forEach(recipeCmd -> recipe.getIngredients().add(ingredientConverter.convert(recipeCmd)) );
        }

        if(recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories()
                    .forEach(categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }
        return recipe;
    }
}
