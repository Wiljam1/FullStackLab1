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

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    //@Transactional
    public User create(User user) {
//        switch (user.getType()) {
//            case PATIENT:
//                Patient patient = new Patient();
//                patient.setBirthdate(user.getPatientProfile().getBirthdate());
//                patientRepository.save(patient);
//                break;
//            case DOCTOR:
//                Doctor doctor = new Doctor();
//                doctor.setFavoriteFruit(user.getDoctorProfile().getFavoriteFruit());
//                doctorRepository.save(doctor);
//                break;
//            default:
//        }

        userRepository.save(user);
        return user;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

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

    public User checkValidLogin(User user) {
        if (user == null) return null;

        User fullUser = findByUserName(user.getUsername());

        if (fullUser != null && user.getPassword().equals(fullUser.getPassword())) {
            return fullUser;
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<User> getUsersWithPatientIdNotNull() {
        return userRepository.findByPatientProfileIsNotNull();
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
