package com.mekontso.recipe.commands;

import com.mekontso.recipe.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Difficulty difficulty;
    private int prepTime;
    private int cookTime;
    private String directions;
    private String serving;
    private String source;
    private Byte[] image;
    private NoteCommand notes;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
}
