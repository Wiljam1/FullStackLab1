package se.kth.wiljam.patientjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.wiljam.patientjournal.model.PatientEncounter;

public interface PatientEncounterRepository extends JpaRepository<PatientEncounter, Long> {
}

