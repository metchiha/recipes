package com.mekontso.recipe.bootstrap;

import com.mekontso.recipe.domain.Category;
import com.mekontso.recipe.domain.UnitOfMeasure;
import com.mekontso.recipe.repositories.CategoryRepository;
import com.mekontso.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(categoryRepository.count() == 0L){
            log.debug("Loading Categories");
            loadCategories();
        }
        if(unitOfMeasureRepository.count() == 0L){
            log.debug("Loading UOMs");
            loadUom();
        }
    }

    private void loadUom() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setUom("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setUom("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setUom("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setUom("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setUom("Ripe");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setUom("Tbsp");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setUom("Serrano");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setUom("Dash");
        unitOfMeasureRepository.save(uom8);

        UnitOfMeasure uom9 = new UnitOfMeasure();
        uom9.setUom("Clove");
        unitOfMeasureRepository.save(uom9);

        UnitOfMeasure uom10 = new UnitOfMeasure();
        uom10.setUom("Pint");
        unitOfMeasureRepository.save(uom10);

        UnitOfMeasure uom11 = new UnitOfMeasure();
        uom11.setUom("Each");
        unitOfMeasureRepository.save(uom11);
    }

    private void loadCategories() {
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

}
