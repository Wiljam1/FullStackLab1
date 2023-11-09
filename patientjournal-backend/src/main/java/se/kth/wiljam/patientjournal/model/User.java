package se.kth.wiljam.patientjournal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private UserType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctorProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patientProfile;

//    public Long getDoctorId() {
//        return doctorProfile.getId();
//    }
//
//    public Long getPatientId() {
//        return patientProfile.getId();
//    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Doctor getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(Doctor doctorProfile) {
        this.doctorProfile = doctorProfile;
    }

    public Patient getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(Patient patientProfile) {
        this.patientProfile = patientProfile;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
