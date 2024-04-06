package org.lemr.lemr_be_one.services;

import org.lemr.lemr_be_one.models.Patient;
import org.lemr.lemr_be_one.repositories.PatientRepository;
import org.lemr.lemr_be_one.requests.NewPatientRequest;
import org.lemr.lemr_be_one.requests.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // GET /patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(String id) {
        return patientRepository.findById(id).orElseThrow();
    }

    // POST /patients
    public void addPatient(NewPatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.name());
        if (request.email() != null) {
            patient.setEmail(request.email());
        }
        if (request.dateOfBirth() != null) {
            patient.setDateOfBirth(request.dateOfBirth());
        }
        if (request.bloodType() != null) {
            patient.setBloodType(request.bloodType());
        }
        if (request.allergies() != null) {
            patient.setAllergies(request.allergies());
        }
        if (request.medicalHistory() != null) {
            patient.setMedicalHistory(request.medicalHistory());
        }
        if (request.emergencyContacts() != null) {
            patient.setEmergencyContacts(request.emergencyContacts());
        }
        if (request.currentMedications() != null) {
            patient.setCurrentMedications(request.currentMedications());
        }
        patientRepository.save(patient);
    }

    // PUT /patients/{id}
    public void updatePatient(String patientId, UpdatePatientRequest request) {
        Optional<Patient> existingPatient = patientRepository.findById(patientId);

        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();

            if (request.name() != null) {
                patient.setName(request.name());
            }
            if (request.email() != null) {
                patient.setEmail(request.email());
            }
            if (request.dateOfBirth() != null) {
                patient.setDateOfBirth(request.dateOfBirth());
            }
            if (request.bloodType() != null) {
                patient.setBloodType(request.bloodType());
            }
            if (request.allergies() != null) {
                patient.setAllergies(request.allergies());
            }
            if (request.medicalHistory() != null) {
                patient.setMedicalHistory(request.medicalHistory());
            }
            if (request.emergencyContacts() != null) {
                patient.setEmergencyContacts(request.emergencyContacts());
            }
            if (request.currentMedications() != null) {
                patient.setCurrentMedications(request.currentMedications());
            }
            patientRepository.save(patient);
        } else {
            throw new RuntimeException("Patient not found with id: " + patientId);
        }
    }

    // DELETE /patients/{id}
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}