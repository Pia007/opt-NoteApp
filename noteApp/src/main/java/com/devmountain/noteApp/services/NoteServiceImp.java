package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NoteDto;
import com.devmountain.noteApp.entities.Note;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.NoteRepository;
import com.devmountain.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

//Part 3d
@Service
public class NoteServiceImp implements NoteService {

    //fields
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    //methods

    @Override
    @Transactional
    public List<NoteDto> getAllNotesByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Note> noteList = noteRepository.findAllByUserEquals(optionalUser.get());
            return noteList.stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addNote(NoteDto noteDto, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Note note = new Note(noteDto);
        optionalUser.ifPresent(note::setUser);
        noteRepository.saveAndFlush(note);
    }

    @Override
    @Transactional
    public void deleteNote(Long noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        optionalNote.ifPresent(note -> noteRepository.delete(note));
    }

    @Override
    @Transactional
    public void updateNote(NoteDto noteDto) {
        Optional<Note> optionalNote = noteRepository.findById(noteDto.getId());
        optionalNote.ifPresent(note -> {
            note.setBody(noteDto.getBody());
            noteRepository.saveAndFlush(note);
        });
    }

    @Override
    @Transactional
    public Optional<NoteDto> getNoteById(Long noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        return optionalNote.map(NoteDto::new);
//        if (optionalNote.isPresent()){
//            return Optional.of(new NoteDto(optionalNote.get()));
//        }
//        return Optional.empty();
    }
}


/*
 Create: NoteServiceImp - find all by user
       1. add a note
       2. delete a note
       3. update a note
       4. find a note by id
       5. find all notes by user
   A. @Service
   B. Use @Autowired to inject dependencies for UserRepository and NoteRepository
   C. Methods
       1. Get All notes by a particular user_Id
            -- @Transactional
            -- public method called getAllNotesByUser that returns an Optional<User> and accepts
                the userId as the arg
            -- Using the Optional container with User type, initialize a variable called optionalUser
                set to the user with the same id in the userRepository
            -- if the optionalUser is present
                -- define a variable called noteList of type List<Note> and set it to the
                    notes in the noteRepository where the notes userId is the same as that
                    the optionalUser
                -- convert the noteList into a stream()
                -- use .map() to take each note and make it a new NoteDto
                -- use collect() to take the notes, results, and place them into a List

                NOTES:
                Stream is a sequence of objects from a source(Collections, Arrays..)
                stream() returns a sequential stream considering collection as the source
                map() maps each element to its corresponding result
                collect() is used to create a list from the results
                Collectors.toList() returns a Collector implementation that accumulates
                    the input elements into a new List.

       2. Adding a note
            -- @Transactional - making sure the transaction is opened and closed
            -- public method with arg noteDto of type NoteDto and userId of type Long
            -- create a new Note and pass noteDto to the constructor
            -- if optional user is present, assign the new note to that user
            -- save and sync the note to the db via the note Repository
       3. Deleting a note
            -- @Transactional
            -- public method with note id of type Long as arg
            -- initialize a variable of type Optional<Note> called optionalNote
                whose value is that of the note with that note id
            -- if a note with that id is found, delete it
       4. Updating a note
            -- @Transactional
            -- public method with noteDto of type NoteDto
            -- initialize a variable of type Optional<Note> called optionalNote
                set to the note with the same noteDto id in the noteRepository
            -- if the note is present, update the body to that of the noteDto passed in
            -- save and sync the note to the db via the note Repository
       5. Get a note by noteId
            -- @Transactional
            -- public method called getNotebyId that returns an Optional<NoteDto> and accepts
                the noteId as the arg
            -- initialize a variable of type Optional<NoteDto> called optionalNote
                set to the note with the same id in the noteRepository
            -- if the optionalNote is present
                return the note
            -- otherwise return the boolean Optional.empty

 */