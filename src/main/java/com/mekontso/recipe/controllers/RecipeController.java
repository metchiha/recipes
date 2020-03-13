package com.mekontso.recipe.controllers;

import com.mekontso.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(Model model, @PathVariable String id){
        log.debug("Getting to the page to show the details of a recipe!");
        model.addAttribute("recipe",recipeService.findById(Long.parseLong(id)));
        return "/recipe/show";
    }
}
