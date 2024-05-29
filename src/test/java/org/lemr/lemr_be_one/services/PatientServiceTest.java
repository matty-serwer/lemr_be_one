package org.lemr.lemr_be_one.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lemr.lemr_be_one.models.Patient;
import org.lemr.lemr_be_one.repositories.PatientRepository;
import org.lemr.lemr_be_one.requests.NewPatientRequest;
import org.lemr.lemr_be_one.requests.UpdatePatientRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testGetAllPatients() {
        // Arrange
        List<Patient> patients = new ArrayList<>();
        when(patientRepository.findAll()).thenReturn(patients);

        // Act
        List<Patient> result = patientService.getAllPatients();

        // Assert
        verify(patientRepository, times(1)).findAll();
        assertSame(patients, result);
    }

    @Test
    public void testGetPatientById() {
        // Arrange
        Patient patient = new Patient();
        when(patientRepository.findById("patientId")).thenReturn(Optional.of(patient));

        // Act
        Patient result = patientService.getPatientById("patientId");

        // Assert
        verify(patientRepository, times(1)).findById("patientId");
        assertSame(patient, result);
    }

    @Test
    public void testGetPatientById_NotFound() {
        // Arrange
        when(patientRepository.findById("patientId")).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> patientService.getPatientById("patientId"));
        verify(patientRepository, times(1)).findById("patientId");
    }

    @Test
    public void testAddPatient() {
        // Arrange
        NewPatientRequest request = new NewPatientRequest("name", "email", null, null, null, null, null, null);

        // Act
        patientService.addPatient(request);

        // Assert
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void testUpdatePatient() {
        // Arrange
        Patient patient = new Patient();
        when(patientRepository.findById("patientId")).thenReturn(Optional.of(patient));
        UpdatePatientRequest request = new UpdatePatientRequest("name", "email", null, null, null, null, null, null);

        // Act
        patientService.updatePatient("patientId", request);

        // Assert
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testUpdatePatient_NotFound() {
        // Arrange
        when(patientRepository.findById("patientId")).thenReturn(Optional.empty());
        UpdatePatientRequest request = new UpdatePatientRequest("name", "email", null, null, null, null, null, null);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> patientService.updatePatient("patientId", request));
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    public void testDeletePatient() {
        // Act
        patientService.deletePatient("patientId");

        // Assert
        verify(patientRepository, times(1)).deleteById("patientId");
    }
}