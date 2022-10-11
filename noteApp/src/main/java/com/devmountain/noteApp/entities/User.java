package com.devmountain.noteApp.entities;
/*
 Part 2
 Entities are objects that represent the data that you want to persist.
 @Table sets the name of the table that the objects will be mapped to.

*/

import com.devmountain.noteApp.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    /* 2b: Fields
       a. define an id for the table and the strategy to generate ids
       b. define username column as unique
       c. define a password column
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    /*2j: define a private Set of type Note called "noteSet"
       set it equal to  a new HashSet*/
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Note> noteSet = new HashSet<>();


    /* 2m: Constructor
        custom constructor to accept the UserDTO arg
        add conditional logic to prevent null pointer exceptions
    */

    public User(UserDto userDto) {
        if (userDto.getUsername() != null) {
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
    }

    /* 2c: Getters/Setters
        generate getters and setters to enable access to the private fields
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

/*
2j: Defining the relationship
 a: Determine which side is the owning side of the relationship
    -- the side that is the "Many" part of the relationship
        -- owns the foreign key
 b: @OneToMany - one user can have many notes
    -- mappedBy - links the Users table to the Notes table, which has the foreign key
    -- fetch = FetchType.LAZY - when the user loads, the notes associated with that user will load on demand
    -- cascade = {CascadeType.MERGE, CascadeType.PERSIST}
       -- merging the user, finds and merges the note associated with that user_id
       -- saving the user, finds and saves the note associate with that user_id
 c: @JsonBackReference prevents infinite recursion when you deliver the
    resource up as JSON through the RESTful API endpoint you will create

2m: Add custom constructor to accept the UserDto arg
        id does not need to be defined when constructing a new obj
    Why? @ID and @GeneratdValue annotations generate the id
    User's "noteSet" has already been initialized to a new HashSet, no need to include
    in constructor. Will be populated in the Service Layer

*/