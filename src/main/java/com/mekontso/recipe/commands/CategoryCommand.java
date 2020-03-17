package com.mekontso.recipe.commands;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;

    private String description;

    private Set<RecipeCommand> recipes;
}
