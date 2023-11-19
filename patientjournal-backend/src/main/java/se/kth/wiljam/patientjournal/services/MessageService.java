package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.Encounter;
import se.kth.wiljam.patientjournal.model.Message;
import se.kth.wiljam.patientjournal.model.User;
import se.kth.wiljam.patientjournal.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message create(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getMessagesBySender(String senderUsername) {
        return messageRepository.findBySenderUsername(senderUsername);
    }

    public List<Message> getMessagesByReceiver(String receiverUsername) {
        return messageRepository.findByReceiverUsername(receiverUsername);
    }

    public List<Message> getMessagesBySenderAndReceiver(String senderUsername, String receiverUsername) {
        return messageRepository.findBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername);
    }

    public List<Message> getMessagesBySubject(String subject) {
        return messageRepository.findBySubject(subject);
    }

    public List<Message> getMessagesBySenderAndReceiverAndSubject(
            String senderUsername, String receiverUsername, String subject) {
        return messageRepository.findBySenderUsernameAndReceiverUsernameAndSubject(
                senderUsername, receiverUsername, subject);
    }

    public List<Message> getConversation(Long senderId, Long receiverId) {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(senderId, receiverId);
    }
}
