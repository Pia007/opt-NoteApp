package com.devmountain.noteApp.entities;

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

    /* 2h: Constructors
        a. create no-arg and all-arg constructors
    */

    public Note() {
    }

    public Note(Long id, String body) {
        this.id = id;
        this.body = body;
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
}

/*
 2i: Defining the One-to-Many relationship
 a: Determine which side is the owning side of the relationship
    -- the side that is the "Many" part of the relationship
        -- owns the foreign key
 b: @ManyToOne creates the association within Hibernate
 c: @JsonBackReference prevents infinite recursion when you deliver the resource up as JSON through the RESTful API endpoint you will create
*/
