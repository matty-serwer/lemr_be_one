package org.lemr.lemr_be_one.services;

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
        return noteRepository.findById(noteId).orElseThrow();
    }

    public List<Note> getNotesByPatientId(String patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    public void addNote(NewNoteRequest request) {
        Note note = new Note();
        note.setPatientId(request.patientId());
        note.setAuthor(request.author());
        note.setType(request.type());
        note.setContent(request.content());
        noteRepository.save(note);
    }

    public void updateNote(String noteId, UpdateNoteRequest request) {
        Note note = noteRepository.findById(noteId).orElseThrow();

        if (request.patientId() != null) {
            note.setPatientId(request.patientId());
        }
        if (request.author() != null) {
            note.setAuthor(request.author());
        }
        if (request.type() != null) {
            note.setType(request.type());
        }
        if (request.content() != null) {
            note.setContent(request.content());
        }

        noteRepository.save(note);
    }

    public void deleteNoteById(String noteId) {
        noteRepository.deleteById(noteId);
    }
}