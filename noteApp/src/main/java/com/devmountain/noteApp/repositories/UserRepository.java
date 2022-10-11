package com.devmountain.noteApp.repositories;


import com.devmountain.noteApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Part 3a
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // method
    Optional<User> findByUsername(String username);

}

/*
3a: Create UserRepository interface
    - annotate with Repository
    - extends JpaRepository<Type, ID_FIELD_TYPE>
        - want JpaRepository to keep track of "User" class and its id field
3f: Creating findByUsername()
    -- the method signature returns an Optional<User>
    -- findByUsername - the username column is unique ( see entity)
        -- using JPA Domain Specific Language - pass in the argument( Type variable_name)
            -- JPA DSL will query the db and search the Users table for the username that
                equals the string
*/
