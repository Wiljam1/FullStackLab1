package se.kth.wiljam.patientjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.wiljam.patientjournal.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
