package com.mekontso.recipe.repositories;

import com.mekontso.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUom() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Tablespoon");
        assertEquals("Tablespoon", uom.get().getUom());
    }

    @Test
    void findByUomCup() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Cup");
        assertEquals("Cup", uom.get().getUom());
    }
}