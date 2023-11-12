package se.kth.wiljam.patientjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.wiljam.patientjournal.model.Message;
import se.kth.wiljam.patientjournal.model.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderUsername(String senderUsername);

    List<Message> findByReceiverUsername(String receiverUsername);

    List<Message> findBySubject(String subject);

    List<Message> findBySenderUsernameAndReceiverUsernameAndSubject(
            String senderUsername, String receiverUsername, String subject);
}
