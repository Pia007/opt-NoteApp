package com.devmountain.noteApp.dtos;

import com.devmountain.noteApp.entities.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//2m
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto implements Serializable {

    //fields
    private Long id;
    private String body;
    private UserDto userDto;

    /*custom constructor accepts a user object
        add conditional logic to prevent null pointer exceptions
        do not include the userDto
           WHY? prevent StackOverFlow errors, infinite recursion
   */

    public NoteDto(Note note) {
        if (note.getId() != null) {
            this.id = note.getId();
        }
        if (note.getBody() != null) {
            this.body = note.getBody();
        }
    }
}
