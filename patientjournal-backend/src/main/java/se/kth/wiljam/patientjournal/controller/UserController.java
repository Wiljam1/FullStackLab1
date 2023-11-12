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

    @GetMapping("user/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

//    @GetMapping("user/{username}")
//    User getUserByUsername(@PathVariable String username) {
//        return userService.getByUsername(username);
//    }

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

    @GetMapping("messages")
    List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    //dålig då flera konversationer kan ha samma subject
    @GetMapping("messages/subject")
    List<Message> getMessagesBySubject(String subject) {
        return messageService.getAllBySubject(subject);
    }

    @GetMapping("messages/{receiver_id}{subject}")
    List<Message> getMessagesByReceiverIdAndSubject(Message message) {
        return messageService.getByReceiverIdAndSubject(message.getReceiver(), message.getSubject());
    }

    @GetMapping("messages/{sender_id}{subject}")
    List<Message> getMessagesBySenderIdAndSubject(Message message) {
        return messageService.getByReceiverIdAndSubject(message.getSender(), message.getSubject());
    }

    //denna är bäst men finns fortfarande problem med att de kan finnas samma ämne
    //tror att man måste kalla på denna två gånger för att få alla meddelanden. byt plats på id då.
    @GetMapping("messages/{sender_id}{receiver_id}{subject}")
    List<Message> getMessagesBySenderIdAndReceiverIdAndSubject(Message message) {
        return messageService.getBySenderIdAndReceiverIdAndSubject(message.getSender(), message.getReceiver(), message.getSubject());
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
