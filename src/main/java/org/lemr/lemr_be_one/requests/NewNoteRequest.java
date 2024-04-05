package org.lemr.lemr_be_one.requests;

import org.lemr.lemr_be_one.models.NoteContent;

import java.util.List;

public record NewNoteRequest(String patientId,
                             String author,
                             String dateTime,
                             String type,
                             List<NoteContent> content) {
}

