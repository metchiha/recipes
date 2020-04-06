package com.mekontso.recipe.controllers;

import com.mekontso.recipe.commands.RecipeCommand;
import com.mekontso.recipe.domain.Recipe;
import com.mekontso.recipe.services.ImageService;
import com.mekontso.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {
    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        controller = new ImageController(imageService,recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void getImageForm() throws Exception{
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void handleImagePost() throws Exception{
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile","testing.txt","text/plain",
                        "Spring framework Guru".getBytes());
        this.mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));
        verify(imageService, times(1)).saveImageFile(anyLong(),any());
    }

    @Test
    public void renderImageFromDB() throws Exception{
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i=0;

        for(byte primByte: s.getBytes()){
            bytesBoxed[i++] = primByte;
        }

        command.setImage(bytesBoxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        byte[] responseByte = response.getContentAsByteArray();
        assertEquals(s.getBytes().length, responseByte.length);
    }

    @Test
    public void testGetImageeNumberFormatException() throws Exception{
        mockMvc.perform(get("/recipe/abvf/recipeimage"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

}