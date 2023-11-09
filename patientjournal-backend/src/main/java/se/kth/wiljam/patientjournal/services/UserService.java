package se.kth.wiljam.patientjournal.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.*;
import se.kth.wiljam.patientjournal.repository.DoctorRepository;
import se.kth.wiljam.patientjournal.repository.PatientRepository;
import se.kth.wiljam.patientjournal.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //@Transactional
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

    /* --- UNUSED METHODS
    public User edit(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public String delete(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted sucessfully!";
    }
    */
}
