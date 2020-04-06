package com.mekontso.recipe.controllers;

import com.mekontso.recipe.commands.RecipeCommand;
import com.mekontso.recipe.domain.Recipe;
import com.mekontso.recipe.exceptions.NotFoundException;
import com.mekontso.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }


    @Test
    void testGetIndexPage() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void testGetRecipe() throws  Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testNewRecipeForm()throws Exception{
        RecipeCommand command = new RecipeCommand();

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception{
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        when(recipeService.saveRecipeCommand(any())).thenReturn(command);
        mockMvc.perform(post("/recipe")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id","2")
        .param("description","some string")
        .param("directions","some directions"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    public void testPostNewRecipeValidationFail() throws Exception{
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        when(recipeService.saveRecipeCommand(any())).thenReturn(command);
        mockMvc.perform(post("/recipe")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id","")
        .param("cookTime", "3000"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipeForm"));
    }

    @Test
    public void testGetUpdateView() throws Exception{
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void deleteAction()throws Exception{
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(recipeService, times(1)).deleteById(anyLong());
    }

    @Test
    public void testGetRecipeNotFound() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetRecipeNotFoundHendler() throws Exception{
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetRecipeNumberFormatException() throws Exception{
        mockMvc.perform(get("/recipe/abvf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

}