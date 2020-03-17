package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.NoteCommand;
import com.mekontso.recipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommand implements Converter<Notes, NoteCommand> {

    private  final RecipeToRecipeCommand recipeConverter;

    public NoteToNoteCommand(RecipeToRecipeCommand recipeConverter) {
        this.recipeConverter = recipeConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Notes notes) {
        if(notes == null)
            return null;
        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(notes.getId());
        noteCommand.setRecipeNotes(notes.getRecipeNotes());
        //noteCommand.setRecipe(recipeConverter.convert(notes.getRecipe()));
        return noteCommand;
    }
}
