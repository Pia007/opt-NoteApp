package com.devmountain.noteApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "Notes")
public class Note {

    /* Fields
       a. define an id for the table and the strategy to generate ids
       b. define body column and set the columnDefinition to "text"
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    /* Constructors
        a. create no-arg and all-arg constructors
    */

    public Note() {
    }

    public Note(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    /* Getters/Setters
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
