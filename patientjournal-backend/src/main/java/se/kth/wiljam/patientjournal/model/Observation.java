package se.kth.wiljam.patientjournal.model;

import jakarta.persistence.*;

@Entity
public class Observation {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Encounter encounter;
    @OneToOne
    private User performer;
    @OneToOne
    private User patient;
    private String subject;
    private String basedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public User getPerformer() {
        return performer;
    }

    public void setPerformer(User performer) {
        this.performer = performer;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBasedOn() {
        return basedOn;
    }

    public void setBasedOn(String basedOn) {
        this.basedOn = basedOn;
    }
}
