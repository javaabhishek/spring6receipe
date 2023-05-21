package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.NotesDto;
import com.asoft.spring6receipe.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotesToNotesDtoUnitTest {

    public static final Long ID_VALUE = 1l;
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesToNotesDto();
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesDto notesDto = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesDto.getId());
        assertEquals(RECIPE_NOTES, notesDto.getRecipeNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }
}