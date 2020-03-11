package com.mekontso.recipe.services;

import com.mekontso.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
