package org.lemr.lemr_be_one.controllers;

import org.lemr.lemr_be_one.exceptions.PatientNotFoundException;
import org.lemr.lemr_be_one.exceptions.InvalidRequestException;
import org.lemr.lemr_be_one.exceptions.PatientFetchException;
import org.lemr.lemr_be_one.exceptions.PatientAddException;
import org.lemr.lemr_be_one.exceptions.PatientUpdateException;
import org.lemr.lemr_be_one.models.Patient;
import org.lemr.lemr_be_one.requests.NewPatientRequest;
import org.lemr.lemr_be_one.requests.UpdatePatientRequest;
import org.lemr.lemr_be_one.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            throw new PatientNotFoundException("Patient not found with id " + id);
        }
    }

    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody NewPatientRequest request) {
        if (request.name() == null || request.name().isEmpty()) {
            throw new InvalidRequestException("Patient name is required");
        }
        patientService.addPatient(request);
        return ResponseEntity.ok("Patient added successfully");
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable String id, @RequestBody UpdatePatientRequest request) {
        if (request.name() == null || request.name().isEmpty()) {
            throw new InvalidRequestException("Patient name is required");
        }
        patientService.updatePatient(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }
}