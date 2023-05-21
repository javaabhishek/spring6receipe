package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.UnitOfMeasureDto;
import com.asoft.spring6receipe.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureToUnitOfMeasureDtoUnitTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1l;

    UnitOfMeasureToUnitOfMeasureDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureDto();
    }

    @Test
    public void testNullObjectConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setUom(DESCRIPTION);
        //when
        UnitOfMeasureDto uomc = converter.convert(uom);

        //then
        assertEquals(LONG_VALUE, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getUom());
    }

}