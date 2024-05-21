package org.lemr.lemr_be_one.requests;

import org.lemr.lemr_be_one.models.NoteContent;

import java.util.List;
import java.util.UUID;

public record NewNoteRequest(String patientId,
                             String author,
                             String type,
                             List<NoteContent> content) {
}

