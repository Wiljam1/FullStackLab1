package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.Observation;
import se.kth.wiljam.patientjournal.repository.ObservationRepository;

import java.util.List;

@Service
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private UserService userService;


    public Observation create(Observation observation) {
        try {
            return observationRepository.save(observation);
        } catch (Exception e) {
            throw new RuntimeException("Error saving Observation: " + observation);
        }
    }

    public List<Observation> getAll() {
        return observationRepository.findAll();
    }

    public Observation getById(Long id) {
        return observationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error getting Observation with id: " + id));
    }

}