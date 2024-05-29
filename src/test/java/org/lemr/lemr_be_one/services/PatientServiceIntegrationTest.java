package org.lemr.lemr_be_one.services;

import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;
import org.lemr.lemr_be_one.models.Patient;
import org.lemr.lemr_be_one.requests.NewPatientRequest;
import org.lemr.lemr_be_one.requests.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PatientServiceIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        assertNotNull(patients);
    }

    @Test
    public void testGetPatientById() {
        Patient patient = patientService.getPatientById(String.valueOf(1L));
        assertNotNull(patient);
    }

    @Test
    public void testAddPatient() {
        NewPatientRequest request = new NewPatientRequest("name", "email", null, null, null, null, null, null);
        patientService.addPatient(request);
        Patient patient = patientService.getPatientById("1");
        Patient savedPatient = patientService.getPatientById(patient.getId());
        assertNotNull(savedPatient);
    }

    @Test
    public void testUpdatePatient() {
        UpdatePatientRequest request = new UpdatePatientRequest("name", "email", null, null, null, null, null, null);
        patientService.updatePatient(String.valueOf(1L), request);
        Patient updatedPatient = patientService.getPatientById(String.valueOf(1L));
        assertNotNull(updatedPatient);
    }

    @Test
    public void testDeletePatient() {
        patientService.deletePatient(String.valueOf(1L));
        Patient deletedPatient = patientService.getPatientById(String.valueOf(1L));
        assertNull(deletedPatient);
    }
}