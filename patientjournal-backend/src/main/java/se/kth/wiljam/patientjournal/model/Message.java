package se.kth.wiljam.patientjournal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //maybe foreign key to user
    private Long senderId;
    //maybe foreign key to user
    private Long receiverId;
    private String subject;
    private String content;
    private Date date;

    public Long getId() {return id;}

    public Long getSender() {return senderId;}

    public Long getReceiver() {return receiverId;}

    public String getContent() {return content;}

    public Date getDate() {return date;}

    public String getSubject() {return subject;}

    public void setId(Long id) {this.id = id;}

    public void setSender(Long senderId) {this.senderId = senderId;}

    public void setReceiver(Long receiverId) {this.receiverId = receiverId;}

    public void setContent(String content) {this.content = content;}

    public void setDate(Date date) {this.date = date;}

    public void setSubject(String subject) {this.subject = subject;}
}
