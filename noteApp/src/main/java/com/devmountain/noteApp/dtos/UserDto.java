package com.devmountain.noteApp.dtos;

import com.devmountain.noteApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//2l
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    //fields
    private Long id;
    private String username;
    private String password;
    private Set<NoteDto> noteDtoSet = new HashSet<>();

    /*custom constructor accepts a user object
        add conditional logic to prevent null pointer exceptions
        do not include the noteDtoSet
           WHY? prevent StackOverFlow errors, infinite recursion
   */

    public UserDto(User user) {
        if (user.getId() != null) {
            this.id = user.getId();
        }
        if (user.getUsername() != null) {
            this.username = user.getUsername();
        }
        if (user.getPassword() !=null) {
            this.password = user.getPassword();
        }
    }
}


/*
2k: Data Transfer Objects - DTOs
    - copy of entity that are used only to transfer the data stored within the object
    WHY?
    Entities are tied to the db. If they are sent outside the application or w/i the application context, there is a risk
    of exposure to vulnerabilities or accidental data changes

    Lombok annotations - @Data, @AllArgsConstructor, @NoArgsConstructor
    simplify the code

    Serializable interface allows this class to be converted to a Bytestream and sent outside the application or stored in a log file
*/