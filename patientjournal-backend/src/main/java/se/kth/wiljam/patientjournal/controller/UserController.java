package se.kth.wiljam.patientjournal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.wiljam.patientjournal.model.*;
import se.kth.wiljam.patientjournal.services.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    @Autowired
    private MessageService messageService;

    //TODO: Använd DTO istället för model-klasserna
    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        System.out.println("Received JSON payload: " + user);
        return userService.create(user);
    }

    // TODO: Implement encounter + condition after every other function done.
    // id is now in the URL! ex: http://localhost:8080/encounter?patientId=1
//    @PostMapping("/encounter/create")
//    Encounter createEncounter(@RequestBody Encounter encounter, @RequestParam Long patientId) {
//        return encounterService.create(encounter, patientId);
//    }

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

    @GetMapping("/patients")
    List<User> getAllPatients() {
        return userService.getUsersWithPatientIdNotNull();
    }

    @GetMapping("/staff")
    List<User> getAllStaff() {
        return userService.getUsersWithStaffIdNotNull();
    }

    @GetMapping("user/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("user123/{username}")
    User getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @GetMapping("encounter/{id}/")
    List<Encounter> getPatientEncounters(@PathVariable Long id) {
        return encounterService.getPatientEncounters(id);
    }

    @PostMapping("/login")
    public ResponseEntity<User> checkLogin(@RequestBody User user) {
        try {
            User validUser = userService.checkValidLogin(user);
            return ResponseEntity.ok(validUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/message")
    Message newMessage(@RequestBody Message message) {
        return messageService.create(message);
    }

    @GetMapping("/messages")
    List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/by-subject")
    List<Message> getMessagesBySubject(@RequestParam String subject) {
        return messageService.getMessagesBySubject(subject);
    }

    @GetMapping("/messages/{receiverUsername}")
    List<Message> getMessagesByReceiver(
            @PathVariable String receiverUsername) {

        return messageService.getMessagesByReceiver(receiverUsername);
    }

    @GetMapping("/messages/{senderUsername}")
    List<Message> getMessagesBySender(
            @PathVariable String senderUsername) {
        return messageService.getMessagesBySender(senderUsername);
    }

    @GetMapping("/messages/{senderUsername}/{receiverUsername}/{subject}")
    List<Message> getMessagesBySenderAndReceiverAndSubject(
            @PathVariable String senderUsername,
            @PathVariable String receiverUsername,
            @PathVariable String subject) {

        return messageService.getMessagesBySenderAndReceiverAndSubject(senderUsername, receiverUsername, subject);
    }


    /* --- UNUSED METHODS
        @PutMapping("user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.edit(newUser, id);
    }

    @DeleteMapping("/user/{id}/")
    String deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
     */
}
