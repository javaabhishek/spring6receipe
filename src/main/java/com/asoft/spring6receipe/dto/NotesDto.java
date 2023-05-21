package com.asoft.spring6receipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotesDto {
    private Long id;
    private String recipeNotes;
}
