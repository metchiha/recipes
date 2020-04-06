package com.mekontso.recipe.services;

import com.mekontso.recipe.converters.RecipeCommandToRecipe;
import com.mekontso.recipe.converters.RecipeToRecipeCommand;
import com.mekontso.recipe.domain.Recipe;
import com.mekontso.recipe.exceptions.NotFoundException;
import com.mekontso.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    void getRecipesTest() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(),1);
        verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getRecipeByIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional= Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull(recipeReturned,"Null recipe returned");
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    public void testDeleteById() throws Exception{
        //Given
        Long idToDelete = Long.valueOf(2L);

        //When
        recipeService.deleteById(idToDelete);

        //No when since the method has no return type
        //Then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }


    @Test
    public void getRecipeByIdTestNotFound() throws Exception{
        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Assertions.assertThrows(NotFoundException.class, () ->recipeService.findById(1L));
    }

}