package com.devmountain.noteApp.repositories;

import com.devmountain.noteApp.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Part3b
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}

/*
3b: Create NoteRepository interface
    - annotate with Repository
    - extends JpaRepository<Type, ID_FIELD_TYPE>
        - want JpaRepository to keep track of "Note" class and its id field

*/
