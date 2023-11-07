package se.kth.wiljam.patientjournal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "condition_table")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User asserter;

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
