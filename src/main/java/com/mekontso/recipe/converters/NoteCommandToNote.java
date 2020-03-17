package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.NoteCommand;
import com.mekontso.recipe.domain.Notes;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Notes> {
    private final RecipeCommandToRecipe recipeConverter;

    public NoteCommandToNote(RecipeCommandToRecipe recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Override
    public Notes convert(NoteCommand noteCommand) {
        if(noteCommand == null)
            return null;
        final Notes notes = new Notes();
        notes.setId(noteCommand.getId());
        notes.setRecipeNotes(noteCommand.getRecipeNotes());
        //notes.setRecipe(recipeConverter.convert(noteCommand.getRecipe()));
        return notes;
    }
}
