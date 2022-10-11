package com.devmountain.noteApp.repositories;


import com.devmountain.noteApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Part 3a
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

/*
3a: Create UserRepository interface
    - annotate with Repository
    - extends JpaRepository<Type, ID_FIELD_TYPE>
        - want JpaRepository to keep track of "User" class and its id field

*/
