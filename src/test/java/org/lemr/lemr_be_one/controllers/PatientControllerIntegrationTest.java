package org.lemr.lemr_be_one.controllers;

import org.junit.jupiter.api.Test;
import org.lemr.lemr_be_one.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllPatients() {
        ResponseEntity<List<Patient>> response = restTemplate.exchange("/api/v1/patients", HttpMethod.GET, null, new ParameterizedTypeReference<List<Patient>>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Patient> patients = response.getBody();
        assertNotNull(patients);
    }

    @Test
    public void testGetPatientById() {
        ResponseEntity<Patient> response = restTemplate.getForEntity("/api/v1/patients/1", Patient.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Patient patient = response.getBody();
        assertNotNull(patient);
    }

    @Test
    public void testAddPatient() {
        Patient patient = new Patient();
        patient.setName("name");
        patient.setEmail("email");
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/patients", patient, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdatePatient() {
        Patient patient = new Patient();
        patient.setName("name");
        patient.setEmail("email");
        restTemplate.put("/api/v1/patients/1", patient);
        ResponseEntity<Patient> response = restTemplate.getForEntity("/api/v1/patients/1", Patient.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Patient updatedPatient = response.getBody();
        assertNotNull(updatedPatient);
    }

    @Test
    public void testDeletePatient() {
        restTemplate.delete("/api/v1/patients/1");
        ResponseEntity<Patient> response = restTemplate.getForEntity("/api/v1/patients/1", Patient.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}