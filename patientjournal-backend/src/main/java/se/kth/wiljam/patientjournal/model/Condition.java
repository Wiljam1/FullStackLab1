package se.kth.wiljam.patientjournal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Condition {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User asserter;
    @OneToOne
    private User patient;
    @OneToOne
    private Encounter encounter;
    private String evidenceDetail;
    private String subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAsserter() {
        return asserter;
    }

    public void setAsserter(User asserter) {
        this.asserter = asserter;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public String getEvidenceDetail() {
        return evidenceDetail;
    }

    public void setEvidenceDetail(String evidenceDetail) {
        this.evidenceDetail = evidenceDetail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
