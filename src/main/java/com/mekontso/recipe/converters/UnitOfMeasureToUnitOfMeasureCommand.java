package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.UnitOfMeasureCommand;
import com.mekontso.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure == null)
            return null;
        final UnitOfMeasureCommand uomCmd = new UnitOfMeasureCommand();
        uomCmd.setId(unitOfMeasure.getId());
        uomCmd.setUom(unitOfMeasure.getUom());
        return uomCmd;
    }
}
