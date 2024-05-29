package org.lemr.lemr_be_one.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lemr.lemr_be_one.exceptions.NoteNotFoundException;
import org.lemr.lemr_be_one.models.Note;
import org.lemr.lemr_be_one.models.NoteContent;
import org.lemr.lemr_be_one.repositories.NoteRepository;
import org.lemr.lemr_be_one.requests.NewNoteRequest;
import org.lemr.lemr_be_one.requests.UpdateNoteRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void testGetNotes() {
        // Arrange
        List<Note> notes = new ArrayList<>();
        when(noteRepository.findAll()).thenReturn(notes);

        // Act
        List<Note> result = noteService.getNotes();

        // Assert
        verify(noteRepository, times(1)).findAll();
        assertSame(notes, result);
    }

    @Test
    public void testGetNotes_EmptyList() {
        // Arrange
        when(noteRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Note> result = noteService.getNotes();

        // Assert
        verify(noteRepository, times(1)).findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetNoteById() {
        // Arrange
        Note note = new Note();
        when(noteRepository.findById("noteId")).thenReturn(Optional.of(note));

        // Act
        Note result = noteService.getNoteById("noteId");

        // Assert
        verify(noteRepository, times(1)).findById("noteId");
        assertSame(note, result);
    }

    @Test
    public void testGetNoteById_NotFound() {
        // Arrange
        when(noteRepository.findById("noteId")).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById("noteId"));
        verify(noteRepository, times(1)).findById("noteId");
    }

    @Test
    public void testGetNotesByPatientId() {
        // Arrange
        List<Note> notes = new ArrayList<>();
        when(noteRepository.findByPatientId("patientId")).thenReturn(notes);

        // Act
        List<Note> result = noteService.getNotesByPatientId("patientId");

        // Assert
        verify(noteRepository, times(1)).findByPatientId("patientId");
        assertSame(notes, result);
    }

    @Test
    public void testGetNotesByPatientId_EmptyList() {
        // Arrange
        when(noteRepository.findByPatientId("patientId")).thenReturn(new ArrayList<>());

        // Act
        List<Note> result = noteService.getNotesByPatientId("patientId");

        // Assert
        verify(noteRepository, times(1)).findByPatientId("patientId");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddNote() {
        // Arrange
        List<NoteContent> content = new ArrayList<>();
        content.add(new NoteContent("Some content")); // Add some content to the note
        NewNoteRequest request = new NewNoteRequest("patientId", "author", "type", content);

        // Act
        noteService.addNote(request);

        // Assert
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void testAddNote_IllegalArgumentException() {
        // Arrange
        NewNoteRequest request = new NewNoteRequest(null, "author", "type", new ArrayList<>());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> noteService.addNote(request));
        verify(noteRepository, never()).save(any(Note.class));
    }

    @Test
    public void testUpdateNote() {
        // Arrange
        Note note = new Note();
        when(noteRepository.findById("noteId")).thenReturn(Optional.of(note));
        UpdateNoteRequest request = new UpdateNoteRequest("patientId", "author", "type", new ArrayList<>());

        // Act
        noteService.updateNote("noteId", request);

        // Assert
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void testUpdateNote_NotFound() {
        // Arrange
        when(noteRepository.findById("noteId")).thenReturn(Optional.empty());
        UpdateNoteRequest request = new UpdateNoteRequest("patientId", "author", "type", new ArrayList<>());

        // Act and Assert
        assertThrows(NoteNotFoundException.class, () -> noteService.updateNote("noteId", request));
        verify(noteRepository, never()).save(any(Note.class));
    }

    @Test
    public void testDeleteNoteById() {
        // Arrange
        Note note = new Note();
        when(noteRepository.findById("noteId")).thenReturn(Optional.of(note));

        // Act
        noteService.deleteNoteById("noteId");

        // Assert
        verify(noteRepository, times(1)).deleteById("noteId");
    }

    @Test
    public void testDeleteNoteById_NotFound() {
        // Arrange
        when(noteRepository.findById("noteId")).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoteNotFoundException.class, () -> noteService.deleteNoteById("noteId"));
        verify(noteRepository, never()).deleteById("noteId");
    }
}