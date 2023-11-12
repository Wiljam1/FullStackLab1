package se.kth.wiljam.patientjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.wiljam.patientjournal.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Get all messages sent by a specific sender
    List<Message> findBySenderId(Long senderId);

    // Get all messages received by a specific receiver
    List<Message> findByReceiverId(Long receiverId);

    // Get all messages by a specific subject
    List<Message> findBySubject(String subject);

    // Get all messages sent by a specific sender and received by a specific receiver
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    // Get all messages by a specific sender and subject
    List<Message> findBySenderIdAndSubject(Long senderId, String subject);

    // Get all messages by a specific receiver and subject
    List<Message> findByReceiverIdAndSubject(Long receiverId, String subject);

    // Get all messages by a specific sender, receiver, and subject
    List<Message> findBySenderIdAndReceiverIdAndSubject(Long senderId, Long receiverId, String subject);
}
