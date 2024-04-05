package org.lemr.lemr_be_one.repositories;

import org.lemr.lemr_be_one.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository
    extends JpaRepository<Note, String> {
}
