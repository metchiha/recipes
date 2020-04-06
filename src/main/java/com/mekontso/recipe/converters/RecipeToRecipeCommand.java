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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NoteToNoteCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(@Lazy NoteToNoteCommand notesConverter, @Lazy CategoryToCategoryCommand categoryConverter,
                                  @Lazy IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }
    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null)
            return null;
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setServing(recipe.getServing());
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime((recipe.getPrepTime()));
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients()
                    .forEach(recip -> recipeCommand.getIngredients().add(ingredientConverter.convert(recip)) );
        }

        if(recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories()
                    .forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }
        return recipeCommand;
    }

}
