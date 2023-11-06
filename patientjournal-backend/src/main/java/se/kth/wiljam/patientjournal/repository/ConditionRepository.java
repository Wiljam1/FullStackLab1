package se.kth.wiljam.patientjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.wiljam.patientjournal.model.Condition;
import se.kth.wiljam.patientjournal.model.User;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
