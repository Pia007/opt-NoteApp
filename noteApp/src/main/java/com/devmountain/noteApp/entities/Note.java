package com.devmountain.noteApp.entities;

import com.devmountain.noteApp.dtos.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

// 2e
@Entity
@Table(name = "Notes")
public class Note {

    /* 2f: Fields
       a. define an id for the table and the strategy to generate ids
       b. define body column and set the columnDefinition to "text"
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    /* 2i: define a private user of type User*/
    @ManyToOne
    @JsonBackReference
    private User user;

    /* 2m: Constructor
        custom constructor to accept the NoteDTO arg
        add conditional logic to prevent null pointer exceptions
    */

    public Note(NoteDto noteDto) {
        if (noteDto.getBody() != null) {
            this.body = noteDto.getBody();
        }
    }


    /* 2g: Getters/Setters
        generate getters and setters to enable access to the private fields
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

/*
 2i: Defining the relationship
 a: Determine which side is the owning side of the relationship
    -- the side that is the "Many" part of the relationship
        -- owns the foreign key
 b: @ManyToOne creates the association within Hibernate
 c: @JsonBackReference prevents infinite recursion when you deliver the
    resource up as JSON through the RESTful API endpoint you will create

2n: Add custom constructor to accept the NoteDto arg
        id does not need to be defined when constructing a new obj
        Why? @ID and @GeneratdValue annotations generate the id
        Note's "user" cannot be defined from the DTO. Will be populated in the Service Layer


*/
