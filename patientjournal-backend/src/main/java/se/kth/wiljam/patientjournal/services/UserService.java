package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.*;
import se.kth.wiljam.patientjournal.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User checkValidLogin(User user) {
        if (user == null) return null;

        User fullUser = userRepository.findByUsername(user.getUsername());
        if (fullUser != null && user.getPassword().equals(fullUser.getPassword())) {
            System.out.println("Logging in as username: " + fullUser.getUsername());
            return fullUser;
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<User> getUsersWithPatientIdNotNull() {
        return userRepository.findByPatientProfileIsNotNull();
    }

}
