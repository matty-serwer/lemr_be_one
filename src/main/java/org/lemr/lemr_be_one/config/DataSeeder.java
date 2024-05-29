package org.lemr.lemr_be_one.config;

import org.lemr.lemr_be_one.models.Note;
import org.lemr.lemr_be_one.models.NoteContent;
import org.lemr.lemr_be_one.models.Patient;
import org.lemr.lemr_be_one.models.EmergencyContact;
import org.lemr.lemr_be_one.repositories.NoteRepository;
import org.lemr.lemr_be_one.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner initDatabase(PatientRepository patientRepository, NoteRepository noteRepository) {
        return args -> {
            if (patientRepository.count() > 0 && noteRepository.count() > 0) {
                // Database is already populated, so we can return early
                return;
            }

            List<Patient> patients = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                Patient patient = new Patient();
                patient.setName("Patient " + i);
                patient.setEmail("patient" + i + "@example.com");
                patient.setDateOfBirth(LocalDate.now().minusYears(20 + i));
                patient.setBloodType("A+");
                patient.setAllergies(Arrays.asList("Pollen", "Dust"));
                patient.setMedicalHistory(Arrays.asList("Asthma", "Diabetes"));
                patient.setEmergencyContacts(Arrays.asList(
                        new EmergencyContact("John Doe", "Father", "1234567890", "johndoe@example.com"),
                        new EmergencyContact("Lorna", "Mother", "Lorna@nabba.com")));
                patient.setCurrentMedications(Arrays.asList("Insulin", "Ventolin"));
                patients.add(patient);
            }
            patientRepository.saveAll(patients);

            List<Note> notes = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Note note = new Note();
                note.setPatientId(patients.get(i % 4).getId());
                note.setAuthor("Author " + i);
                note.setCreatedAt(ZonedDateTime.now());
                note.setUpdatedAt(ZonedDateTime.now());
                note.setType("Type " + i);
                note.setContent(Arrays.asList(new NoteContent("This is some example note content text. Lorem Ipsum Epsilon Jabroni" + i)));
                notes.add(note);
            }
            noteRepository.saveAll(notes);
        };
    }
}