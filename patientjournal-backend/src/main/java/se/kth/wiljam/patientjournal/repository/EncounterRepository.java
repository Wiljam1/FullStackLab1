package se.kth.wiljam.patientjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.wiljam.patientjournal.model.Encounter;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
}
