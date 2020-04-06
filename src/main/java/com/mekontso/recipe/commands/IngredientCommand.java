package com.mekontso.recipe.commands;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;

    private Long recipeId;

    private String description;
    private BigDecimal amount;
    private RecipeCommand recipe;
    private UnitOfMeasureCommand unitOfMeasure;
}
