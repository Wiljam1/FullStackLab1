package se.kth.wiljam.patientjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.kth.wiljam.patientjournal.model.Message;
import se.kth.wiljam.patientjournal.model.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderUsername(String senderUsername);

    List<Message> findByReceiverUsername(String receiverUsername);

    List<Message> findBySubject(String subject);

    List<Message> findBySenderUsernameAndReceiverUsernameAndSubject(
            String senderUsername, String receiverUsername, String subject);

    List<Message> findBySenderUsernameAndReceiverUsername(String senderUsername, String receiverUsername);

    @Query("SELECT m FROM Message m " +
            "WHERE (m.sender.id = :senderId AND m.receiver.id = :receiverId) " +
            "OR (m.receiver.id = :senderId AND m.sender.id = :receiverId)")
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
            @Param("senderId") Long senderId,
            @Param("receiverId") Long receiverId
    );
}
