package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.Observation;
import se.kth.wiljam.patientjournal.model.User;
import se.kth.wiljam.patientjournal.repository.ConditionRepository;
import se.kth.wiljam.patientjournal.repository.EncounterRepository;
import se.kth.wiljam.patientjournal.repository.ObservationRepository;
import se.kth.wiljam.patientjournal.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {

    //Kanske borde göra flera services för varje repository
    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User newUser(User newUser) {
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public String deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted sucessfully!";
    }

    public User checkValidLogin(String userName, String password) {
        User user = findByUserName(userName);
        if (user == null){
            throw new NoSuchElementException();
        }
        if (password.equals(user.getPassword())){
            return user;
        }
        return null;
    }

    //TODO: very ineffective so should get rewritten
    private User findByUserName(String userName) {
        List<User> users = userRepository.findAll();
        for (User user: users){
            if (user.getUsername().equals(userName)){
                return user;
            }
        }
        return null;
    }
}
