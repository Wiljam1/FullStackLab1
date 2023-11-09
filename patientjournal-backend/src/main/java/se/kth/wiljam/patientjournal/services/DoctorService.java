package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.Doctor;
import se.kth.wiljam.patientjournal.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor create(Doctor patient) {
        return doctorRepository.save(patient);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public Doctor getById(Long id) {
        return doctorRepository.findById(id)
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
