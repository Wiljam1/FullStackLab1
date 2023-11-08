package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.*;
import se.kth.wiljam.patientjournal.repository.*;

import java.util.List;

@Service
public class EncounterService {

    @Autowired
    private EncounterRepository encounterRepository;

    //Vet inte om man ska ha service eller repository
    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientEncounterRepository patientEncounterRepository;


    public Encounter create(Encounter encounter, Long patientId) {
        Long practitionerId = encounter.getPractitioner().getId();
        encounter.setPractitioner(userService.getById(practitionerId));
        Encounter createdEncounter = encounterRepository.save(encounter);

        Patient patient = patientService.getById(patientId);
        PatientEncounter patientEncounter = new PatientEncounter();
        patientEncounter.setPatient(patient);
        patientEncounter.setEncounter(createdEncounter);
        patientEncounterRepository.save(patientEncounter);

        return createdEncounter;
    }

    public List<Encounter> getAll() {
        return encounterRepository.findAll();
    }

    public Encounter getById(Long id) {
        return encounterRepository.findById(id)
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

    public String delete(Long id) {
        if(!encounterRepository.existsById(id)) {
            throw new UserNotFoundException(id); //TODO: Change exception
        }
        encounterRepository.deleteById(id);
        return "Observation with id " + id + " has been deleted sucessfully!";
    }

}
