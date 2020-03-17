package com.mekontso.recipe.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {
    private Long id;
    private String recipeNotes;
    private RecipeCommand recipe;
}
