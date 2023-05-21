package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.UnitOfMeasureDto;
import com.asoft.spring6receipe.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureDtoToUnitOfMeasureUnitTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1l;

    UnitOfMeasureDtoToUnitOfMeasure converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureDtoToUnitOfMeasure();

    }

    @Test
    public void testNullParamter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureDto Dto = new UnitOfMeasureDto();
        Dto.setId(LONG_VALUE);
        Dto.setUom(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(Dto);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getUom());

    }

}