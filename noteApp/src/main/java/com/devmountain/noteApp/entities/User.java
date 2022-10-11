package com.devmountain.noteApp.entities;
/*
 Part 2
 Entities are objects that represent the data that you want to persist.
 @Table sets the name of the table that the objects will be mapped to.

*/

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


    /* 2d: Constructors
        a. create no-arg and all-arg constructors
    */

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
