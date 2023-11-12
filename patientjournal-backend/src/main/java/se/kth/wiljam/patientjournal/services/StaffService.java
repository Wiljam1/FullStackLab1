package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.Staff;
import se.kth.wiljam.patientjournal.repository.StaffRepository;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff create(Staff staff) {
        return staffRepository.save(staff);
    }

    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    public Staff getById(Long id) {
        return staffRepository.findById(id)
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
