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
    private MessageService messageService;

    @PostMapping("/user")
    User newUser(@RequestBody User user) {
        System.out.println("Received JSON payload: " + user);
        return userService.create(user);
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

    @GetMapping("user123/{username}")
    User getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
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

    @GetMapping("/messages/{senderUsername}")
    List<Message> getMessagesBySender(
            @PathVariable String senderUsername) {
        return messageService.getMessagesBySender(senderUsername);
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    List<Message> getConversation(
            @PathVariable Long senderId,
            @PathVariable Long receiverId) {

        return messageService.getConversation(senderId, receiverId);
    }

    @GetMapping("/messages/{senderUsername}/{receiverUsername}/{subject}")
    List<Message> getMessagesBySenderAndReceiverAndSubject(
            @PathVariable String senderUsername,
            @PathVariable String receiverUsername,
            @PathVariable String subject) {

        return messageService.getMessagesBySenderAndReceiverAndSubject(senderUsername, receiverUsername, subject);
    }
}
