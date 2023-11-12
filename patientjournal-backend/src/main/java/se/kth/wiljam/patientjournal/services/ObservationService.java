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
    @Autowired
    private PatientService patientService;
    @Autowired
    private StaffService staffService;


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
                .orElseThrow(() -> new UserNotFoundException(id)); //TODO: Change exception
    }

//    public Observation update(Observation newUser, Long id) {
//        return observationRepository.findById(id)
//                .map(user -> {
//                    user.setName(newUser.getName());
//                    user.setUsername(newUser.getUsername());
//                    user.setEmail(newUser.getEmail());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    public String delete(Long id) {
//        if(!observationRepository.existsById(id)) {
//            throw new UserNotFoundException(id); //TODO: Change exception
//        }
//        observationRepository.deleteById(id);
//        return "Observation with id " + id + " has been deleted sucessfully!";
//    }

}
