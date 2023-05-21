package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.UnitOfMeasureDto;
import com.asoft.spring6receipe.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureDto implements Converter<UnitOfMeasure, UnitOfMeasureDto> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureDto convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure != null) {
            final UnitOfMeasureDto uomc = new UnitOfMeasureDto();
            uomc.setId(unitOfMeasure.getId());
            uomc.setUom(unitOfMeasure.getUom());
            return uomc;
        }
        return null;
    }
}