package com.mekontso.recipe.converters;

import com.mekontso.recipe.commands.UnitOfMeasureCommand;
import com.mekontso.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand,UnitOfMeasure> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand == null){
            return null;
        }else{
            final UnitOfMeasure uom = new UnitOfMeasure();
            uom.setId(unitOfMeasureCommand.getId());
            uom.setUom(unitOfMeasureCommand.getUom());
            return uom;
        }
    }
}
