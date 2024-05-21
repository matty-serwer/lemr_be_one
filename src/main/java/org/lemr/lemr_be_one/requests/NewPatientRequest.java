package org.lemr.lemr_be_one.requests;

import org.lemr.lemr_be_one.models.EmergencyContact;

import java.time.LocalDate;
import java.util.List;

public record NewPatientRequest(String name,
                                String email,
                                LocalDate dateOfBirth,
                                String bloodType,
                                List<String> allergies,
                                List<String> medicalHistory,
                                List<EmergencyContact> emergencyContacts,
                                List<String> currentMedications) {
}