package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.Observation;
import se.kth.wiljam.patientjournal.model.Patient;
import se.kth.wiljam.patientjournal.model.User;
import se.kth.wiljam.patientjournal.repository.*;

import java.util.List;



@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

//    public User updateUser(User newUser, Long id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setName(newUser.getName());
//                    user.setUsername(newUser.getUsername());
//                    user.setEmail(newUser.getEmail());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    public String deleteUser(Long id) {
//        if(!userRepository.existsById(id)) {
//            throw new UserNotFoundException(id);
//        }
//        userRepository.deleteById(id);
//        return "User with id " + id + " has been deleted sucessfully!";
//    }

}
