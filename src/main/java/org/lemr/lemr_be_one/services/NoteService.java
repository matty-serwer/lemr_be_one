package org.lemr.lemr_be_one.services;

import org.lemr.lemr_be_one.exceptions.NoteNotFoundException;
import org.lemr.lemr_be_one.models.Note;
import org.lemr.lemr_be_one.repositories.NoteRepository;
import org.lemr.lemr_be_one.requests.NewNoteRequest;
import org.lemr.lemr_be_one.requests.UpdateNoteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // GET /notes
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note with ID " + noteId + " not found"));
    }

    public List<Note> getNotesByPatientId(String patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    public void addNote(NewNoteRequest request) {
        if (request.patientId() == null || request.patientId().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be empty");
        }
        if (request.author() == null || request.author().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (request.type() == null || request.type().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        if (request.content() == null || request.content().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }

        Note note = new Note();
        note.setPatientId(request.patientId());
        note.setAuthor(request.author());
        note.setType(request.type());
        note.setContent(request.content());
        noteRepository.save(note);
    }

    public void updateNote(String noteId, UpdateNoteRequest request) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note with ID " + noteId + " not found"));

        if (request.patientId() != null && !request.patientId().isEmpty()) {
            note.setPatientId(request.patientId());
        }
        if (request.author() != null && !request.author().isEmpty()) {
            note.setAuthor(request.author());
        }
        if (request.type() != null && !request.type().isEmpty()) {
            note.setType(request.type());
        }
        if (request.content() != null && !request.content().isEmpty()) {
            note.setContent(request.content());
        }

        noteRepository.save(note);
    }

    public void deleteNoteById(String noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note with ID " + noteId + " not found"));
        noteRepository.deleteById(noteId);
    }
}