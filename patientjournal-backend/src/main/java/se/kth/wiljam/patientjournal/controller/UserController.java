package se.kth.wiljam.patientjournal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.wiljam.patientjournal.model.Encounter;
import se.kth.wiljam.patientjournal.model.Observation;
import se.kth.wiljam.patientjournal.model.User;
import se.kth.wiljam.patientjournal.services.EncounterService;
import se.kth.wiljam.patientjournal.services.ObservationService;
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

    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        return userService.newUser(user);
    }

    @PostMapping("/encounter")
    Encounter createEncounter(@RequestBody Encounter encounter) {
        return encounterService.create(encounter);
    }

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
        return userService.getUserById(id);
    }

    @PutMapping("user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/user/{id}/")
    String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
