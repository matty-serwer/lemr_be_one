package org.lemr.lemr_be_one.config;

import org.lemr.lemr_be_one.models.Note;
import org.lemr.lemr_be_one.models.NoteContent;
import org.lemr.lemr_be_one.repositories.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final NoteRepository noteRepository;

    public DatabaseSeeder(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (noteRepository.count() == 0) {
            List<Note> notes = Arrays.asList(
                    new Note("1", "Patient1", "Dr. John Doe", ZonedDateTime.now(), "Medical", Arrays.asList(new NoteContent("Patient reported feeling unwell."), new NoteContent("Prescribed rest and hydration."))),
                    new Note("2", "Patient2", "Dr. Jane Doe", ZonedDateTime.now(), "Medical", Arrays.asList(new NoteContent("Patient reported headache."), new NoteContent("Prescribed painkillers."))),
                    new Note("3", "Patient3", "Dr. John Smith", ZonedDateTime.now(), "Medical", Arrays.asList(new NoteContent("Patient reported stomach ache."), new NoteContent("Prescribed antacids."))),
                    new Note("4", "Patient4", "Dr. Jane Smith", ZonedDateTime.now(), "Medical", Arrays.asList(new NoteContent("Patient reported feeling tired."), new NoteContent("Prescribed rest.")))
            );

            noteRepository.saveAll(notes);
        }
    }
}