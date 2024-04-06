package org.lemr.lemr_be_one.repositories;

import org.lemr.lemr_be_one.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}