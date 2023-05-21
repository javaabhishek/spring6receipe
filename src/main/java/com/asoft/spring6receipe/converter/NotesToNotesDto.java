package com.asoft.spring6receipe.converter;

import com.asoft.spring6receipe.dto.NotesDto;
import com.asoft.spring6receipe.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesDto implements Converter<Notes, NotesDto> {

    @Synchronized
    @Nullable
    @Override
    public NotesDto convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesDto notesDto = new NotesDto();
        notesDto.setId(source.getId());
        notesDto.setRecipeNotes(source.getRecipeNotes());
        return notesDto;
    }
}