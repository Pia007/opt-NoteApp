package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NoteDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NoteService {
    //methods
    @Transactional
    void addNote(NoteDto noteDto, Long userId);

    @Transactional
    void deleteNote(Long noteId);

    @Transactional
    void updateNote(NoteDto noteDto);

    @Transactional
    Optional<NoteDto> getNoteById(Long noteId);

    @Transactional
    List<NoteDto> getAllNotesByUserId(Long userId);
}
