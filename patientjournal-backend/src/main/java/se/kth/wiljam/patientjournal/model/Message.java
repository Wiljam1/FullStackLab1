package se.kth.wiljam.patientjournal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "sent-messages")
    private User sender;

    @ManyToOne
    @JsonBackReference(value = "received-messages")
    private User receiver;

    private String subject;
    private String content;
    private Date date;

    public Long getId() {return id;}

    public User getSender() {return sender;}

    public User getReceiver() {return receiver;}

    public String getContent() {return content;}

    public Date getDate() {return date;}

    public String getSubject() {return subject;}

    public void setId(Long id) {this.id = id;}

    public void setSender(User sender) {this.sender = sender;}

    public void setReceiver(User receiver) {this.receiver = receiver;}

    public void setContent(String content) {this.content = content;}

    public void setDate(Date date) {this.date = date;}

    public void setSubject(String subject) {this.subject = subject;}


}
