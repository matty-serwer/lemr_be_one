package org.lemr.lemr_be_one.repositories;

import org.junit.jupiter.api.Test;
import org.lemr.lemr_be_one.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PatientRepositoryIntegrationTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testFindAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        assertNotNull(patients);
    }

    @Test
    public void testFindPatientById() {
        Patient patient = new Patient();
        patient.setName("name");
        patient.setEmail("email");
        patientRepository.save(patient);
        Patient foundPatient = patientRepository.findById(patient.getId()).orElse(null);
        assertNotNull(foundPatient);
    }

    @Test
    public void testSavePatient() {
        Patient patient = new Patient();
        patient.setName("name");
        patient.setEmail("email");
        patientRepository.save(patient);
        Patient savedPatient = patientRepository.findById(patient.getId()).orElse(null);
        assertNotNull(savedPatient);
    }

    @Test
    public void testDeletePatient() {
        patientRepository.deleteById(String.valueOf(1L));
        Patient deletedPatient = patientRepository.findById(String.valueOf(1L)).orElse(null);
        assertNull(deletedPatient);
    }
}