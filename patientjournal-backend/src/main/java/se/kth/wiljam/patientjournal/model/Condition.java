package se.kth.wiljam.patientjournal.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "condition_table")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "asserter_id")
    private User asserter;

    @ManyToMany
    @JoinTable(name = "condition_encounter",
            joinColumns = @JoinColumn(name = "condition_id"),
            inverseJoinColumns = @JoinColumn(name = "encounter_id")
    )
    private List<Encounter> encounters;
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

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
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
