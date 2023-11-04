package se.kth.wiljam.patientjournal.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("Could not find the user with ID: " + id);
    }
}
