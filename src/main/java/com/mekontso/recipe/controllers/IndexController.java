package com.mekontso.recipe.controllers;

import com.mekontso.recipe.domain.Category;
import com.mekontso.recipe.domain.UnitOfMeasure;
import com.mekontso.recipe.repositories.CategoryRepository;
import com.mekontso.recipe.repositories.UnitOfMeasureRepository;
import com.mekontso.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {
 /*
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }*/
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Getting to the Homa page named index!");
        model.addAttribute("recipes", recipeService.getRecipes());
        return  "index";
        /*
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        System.out.println("Cat id is : " + categoryOptional.get().getId());
        System.out.println("Unit of Measure id is : " + unitOfMeasureOptional.get().getId());
        return "index";*/


    }
}
