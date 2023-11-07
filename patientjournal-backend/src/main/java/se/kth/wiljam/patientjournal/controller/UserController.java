package se.kth.wiljam.patientjournal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.wiljam.patientjournal.model.Encounter;
import se.kth.wiljam.patientjournal.model.Observation;
import se.kth.wiljam.patientjournal.model.Patient;
import se.kth.wiljam.patientjournal.model.User;
import se.kth.wiljam.patientjournal.services.EncounterService;
import se.kth.wiljam.patientjournal.services.ObservationService;
import se.kth.wiljam.patientjournal.services.PatientService;
import se.kth.wiljam.patientjournal.services.UserService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ObservationService observationService;
    @Autowired
    private EncounterService encounterService;
    @Autowired
    private PatientService patientService;

    //TODO: Använd DTO istället för model-klasserna
    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        return userService.create(user);
    }

    // id is now in the URL! ex: http://localhost:8080/encounter?patientId=1
    @PostMapping("/encounter")
    Encounter createEncounter(@RequestBody Encounter encounter, @RequestParam Long patientId) {
        return encounterService.create(encounter, patientId);
    }

//    @PostMapping("/encounter")
//    Encounter createEncounter(@RequestBody Encounter encounter) {
//        Long patientId = encounter.getPatientId();
//        return encounterService.create(encounter, patientId);
//    }


    @PostMapping("/observation")
    Observation createObservation(@RequestBody Observation observation) {
        return observationService.create(observation);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("user/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.edit(newUser, id);
    }

    @DeleteMapping("/user/{id}/")
    String deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}
