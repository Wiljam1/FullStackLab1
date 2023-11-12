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

    public List<Message> getAllMessagesBySender(Long sender_id) {
        return messageRepository.findBySenderId(sender_id);
    }

    public List<Message> getAllMessagesByReceiver(Long receiver_id) {
        return messageRepository.findByReceiverId(receiver_id);
    }

    public List<Message> getAllBySubject(String subject) {
        return messageRepository.findBySubject(subject);
    }

    public List<Message> getBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public List<Message> getBySenderIdAndSubject(Long senderId, String subject) {
        return messageRepository.findBySenderIdAndSubject(senderId, subject);
    }

    public List<Message> getByReceiverIdAndSubject(Long receiverId, String subject) {
        return messageRepository.findByReceiverIdAndSubject(receiverId, subject);
    }

    public List<Message> getBySenderIdAndReceiverIdAndSubject(Long senderId, Long receiverId, String subject) {
        return messageRepository.findBySenderIdAndReceiverIdAndSubject(senderId, receiverId, subject);
    }
}
