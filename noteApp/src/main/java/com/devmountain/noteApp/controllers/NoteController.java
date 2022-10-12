package com.devmountain.noteApp.controllers;

import com.devmountain.noteApp.dtos.NoteDto;
import com.devmountain.noteApp.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/user/{userId}")
    public List<NoteDto> getAllNotesByUserId(@PathVariable Long userId) {
        return noteService.getAllNotesByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public void addNote(@RequestBody NoteDto noteDto, @PathVariable Long userId) {
        noteService.addNote(noteDto, userId);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
    }

    @PutMapping
    public void updateNote(@RequestBody NoteDto noteDto) {
        noteService.updateNote(noteDto);
    }

    @GetMapping("/{noteId}")
    public Optional<NoteDto> getNoteById(@PathVariable Long noteId) {
        return noteService.getNoteById(noteId);
    }



}

/*
Notes Controller
-- use @RestController
-- use @RequestMapping - endpoint "api/v1/notes
-- Note: @RequestParam vs @PathVariable
    @RequestParam - extracts static values from th query string
        localhost:8080/api/v1/notes/user?id=2
        @RequestParam Long userId
    @PathVariable - extracts dynamic values from the URI path
        localhost:8080/api/v1/notes/user/2
        @PathVariable Long userId
Process?
1. Must use Autowire the dependencies that the controllers need
    a: NoteService - controller need to interact with the ServiceLayer

2. Get request - get notes created by a particular user
    a: public method that returns List<NoteDto> called getNotesByUser
    b: @GetMapping with endpoint "/user/{userId}"
    c: returns getNotesByUser() on the noteService dependency and pass in the userId

3. Post request - add a new note
    a: public method called addNote that takes 2 args
        1. noteDto of Type NoteDto annotate with @RequestBody - maps the JSON obj on the req to the DTO and makes it usable
        2. userId of Type Long annotated with @PathVariable - indicates which user is posting the note
    b: @PostMapping with endpoint "/user/{userId}"
    c: use addNote() on the noteService dependency with "userDto" and "userId" as the args to add the note

4. Delete request - delete a note
    a: public method called deleteNote that accepts an @PathVariable annotated noteId of Type Long
    b: @DeleteMapping with endpoint "/{noteId}"
    c: use deleteNote() on the noteService dependency with the noteId as the arg to delete the note

5. Put request - update a note
    a: public method called updateNote that accepts an @RequestBody annotated noteDto of Type NoteDto
    b: @PutMapping
    c: use updateNote() on the noteService with noteDto as the arg to update that note

6: Get request - get note by id
    1: public method that returns Optional<NoteDto> called getNote that takes @PathVariable annotated noteId of Type Long
    2: @GetMapping with endpoint "/{noteId}
    3: use getNoteById() on the noteService with noteDto as the arg to get that note




 */
