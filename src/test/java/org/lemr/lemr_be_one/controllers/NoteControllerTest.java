package org.lemr.lemr_be_one.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lemr.lemr_be_one.exceptions.InvalidRequestException;
import org.lemr.lemr_be_one.models.Note;
import org.lemr.lemr_be_one.models.NoteContent;
import org.lemr.lemr_be_one.requests.NewNoteRequest;
import org.lemr.lemr_be_one.requests.UpdateNoteRequest;
import org.lemr.lemr_be_one.services.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    @Test
    public void testGetNotes() {
        // Arrange
        List<Note> notes = new ArrayList<>();
        when(noteService.getNotes()).thenReturn(notes);

        // Act
        List<Note> result = noteController.getNotes();

        // Assert
        verify(noteService, times(1)).getNotes();
        assertSame(notes, result);
    }

    @Test
    public void testGetNoteById() {
        // Arrange
        Note note = new Note();
        when(noteService.getNoteById("noteId")).thenReturn(note);

        // Act
        Note result = noteController.getNoteById("noteId");

        // Assert
        verify(noteService, times(1)).getNoteById("noteId");
        assertSame(note, result);
    }

    @Test
    public void testGetNotesByPatientId() {
        // Arrange
        List<Note> notes = new ArrayList<>();
        when(noteService.getNotesByPatientId("patientId")).thenReturn(notes);

        // Act
        List<Note> result = noteController.getNotesByPatientId("patientId");

        // Assert
        verify(noteService, times(1)).getNotesByPatientId("patientId");
        assertSame(notes, result);
    }

    @Test
    public void testAddNote() {
        // Arrange
        List<NoteContent> content = new ArrayList<>();
        NewNoteRequest request = new NewNoteRequest("patientId", "author", "type", content);

        // Act
        noteController.addNote(request);

        // Assert
        verify(noteService, times(1)).addNote(request);
    }

    @Test
    public void testAddNote_InvalidRequestException() {
        // Arrange
        List<NoteContent> content = new ArrayList<>();
        NewNoteRequest request = new NewNoteRequest(null, "author", "type", content);

        // Act and Assert
        assertThrows(InvalidRequestException.class, () -> noteController.addNote(request));
        verify(noteService, never()).addNote(request);
    }

    @Test
    public void testUpdateNote() {
        // Arrange
        List<NoteContent> content = new ArrayList<>();
        UpdateNoteRequest request = new UpdateNoteRequest("patientId", "author", "type", content);

        // Act
        noteController.updateNote("noteId", request);

        // Assert
        verify(noteService, times(1)).updateNote("noteId", request);
    }

    @Test
    public void testUpdateNote_InvalidRequestException() {
        // Arrange
        List<NoteContent> content = new ArrayList<>();
        UpdateNoteRequest request = new UpdateNoteRequest(null, "author", "type", content);

        // Act and Assert
        assertThrows(InvalidRequestException.class, () -> noteController.updateNote("noteId", request));
        verify(noteService, never()).updateNote("noteId", request);
    }
}