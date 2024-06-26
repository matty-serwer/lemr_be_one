package org.lemr.lemr_be_one.controllers;

import org.lemr.lemr_be_one.exceptions.InvalidRequestException;
import org.lemr.lemr_be_one.models.Note;
import org.lemr.lemr_be_one.requests.NewNoteRequest;
import org.lemr.lemr_be_one.requests.UpdateNoteRequest;
import org.lemr.lemr_be_one.services.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping(path = "{noteId}")
    public Note getNoteById(@PathVariable("noteId") String noteId) {
        return noteService.getNoteById(noteId);
    }

    @GetMapping(path = "byPatient/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable("patientId") String patientId) {
        return noteService.getNotesByPatientId(patientId);
    }

    @PostMapping
    public void addNote(@RequestBody NewNoteRequest request) {
        if (request.patientId() == null || request.patientId().isEmpty()) {
            throw new InvalidRequestException("Patient id is required");
        }
        noteService.addNote(request);
    }

    @PutMapping(path = "{noteId}")
    public void updateNote(@PathVariable("noteId") String noteId, @RequestBody UpdateNoteRequest request) {
        if (request.patientId() == null || request.patientId().isEmpty()) {
            throw new InvalidRequestException("Patient id is required");
        }
        noteService.updateNote(noteId, request);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNoteById(@PathVariable("noteId") String noteId) {
        noteService.deleteNoteById(noteId);
    }
}