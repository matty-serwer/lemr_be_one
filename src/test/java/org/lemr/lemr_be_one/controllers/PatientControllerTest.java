package org.lemr.lemr_be_one.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lemr.lemr_be_one.exceptions.InvalidRequestException;
import org.lemr.lemr_be_one.exceptions.PatientNotFoundException;
import org.lemr.lemr_be_one.models.Patient;
import org.lemr.lemr_be_one.requests.NewPatientRequest;
import org.lemr.lemr_be_one.requests.UpdatePatientRequest;
import org.lemr.lemr_be_one.services.PatientService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @Test
    public void testGetAllPatients() {
        // Arrange
        List<Patient> patients = new ArrayList<>();
        when(patientService.getAllPatients()).thenReturn(patients);

        // Act
        List<Patient> result = patientController.getAllPatients();

        // Assert
        verify(patientService, times(1)).getAllPatients();
        assertSame(patients, result);
    }

    @Test
    public void testGetPatientById() {
        // Arrange
        Patient patient = new Patient();
        when(patientService.getPatientById("patientId")).thenReturn(patient);

        // Act
        ResponseEntity<Patient> result = patientController.getPatientById("patientId");

        // Assert
        verify(patientService, times(1)).getPatientById("patientId");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(patient, result.getBody());
    }

    @Test
    public void testGetPatientById_NotFound() {
        // Arrange
        when(patientService.getPatientById("patientId")).thenReturn(null);

        // Act and Assert
        assertThrows(PatientNotFoundException.class, () -> patientController.getPatientById("patientId"));
        verify(patientService, times(1)).getPatientById("patientId");
    }

    @Test
    public void testAddPatient() {
        // Arrange
        NewPatientRequest request = new NewPatientRequest("name", "email", null, null, null, null, null, null);

        // Act
        ResponseEntity<String> result = patientController.addPatient(request);

        // Assert
        verify(patientService, times(1)).addPatient(request);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Patient added successfully", result.getBody());
    }

    @Test
    public void testAddPatient_InvalidRequestException() {
        // Arrange
        NewPatientRequest request = new NewPatientRequest(null, "email", null, null, null, null, null, null);

        // Act and Assert
        assertThrows(InvalidRequestException.class, () -> patientController.addPatient(request));
        verify(patientService, never()).addPatient(request);
    }

    @Test
    public void testUpdatePatient() {
        // Arrange
        UpdatePatientRequest request = new UpdatePatientRequest("name", "email", null, null, null, null, null, null);

        // Act
        patientController.updatePatient("patientId", request);

        // Assert
        verify(patientService, times(1)).updatePatient("patientId", request);
    }

    @Test
    public void testUpdatePatient_InvalidRequestException() {
        // Arrange
        UpdatePatientRequest request = new UpdatePatientRequest(null, "email", null, null, null, null, null, null);

        // Act and Assert
        assertThrows(InvalidRequestException.class, () -> patientController.updatePatient("patientId", request));
        verify(patientService, never()).updatePatient("patientId", request);
    }

    @Test
    public void testDeletePatient() {
        // Act
        patientController.deletePatient("patientId");

        // Assert
        verify(patientService, times(1)).deletePatient("patientId");
    }
}