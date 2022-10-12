package com.devmountain.noteApp.repositories;

import com.devmountain.noteApp.entities.Note;
import com.devmountain.noteApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Part3b
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserEquals(User user);
}

/*
3b: Create NoteRepository interface
    - annotate with Repository
    - extends JpaRepository<Type, ID_FIELD_TYPE>
        - want JpaRepository to keep track of "Note" class and its id field

*/
