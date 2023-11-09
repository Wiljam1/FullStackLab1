package se.kth.wiljam.patientjournal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(
        name = "OBSERVATIONS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"patient_id", "doctor_id", "subject"})}
)
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor performer;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String subject;
    private String basedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getPerformer() {
        return performer;
    }

    public void setPerformer(Doctor performer) {
        this.performer = performer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
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
