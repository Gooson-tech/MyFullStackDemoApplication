package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "personForm")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class StudentRegistration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "First name must not be empty")
    String firstName = "";
    @NotNull
    @NotEmpty(message = "Last name must not be empty")
    String lastName = "";
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be a valid email")
    String email = "";
    @NotEmpty(message = "Semester must not be empty")
    String semester = "";

    @NotEmpty(message = "Course must not be empty")
    String course = "";
    @Size(max = 400)
    String note = "";


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourse() {
        return course;
    }

    public String getNote() {
        return note;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StudentRegistration() {}

    public Long getId() {
        return id;
    }

    public StudentRegistration(String firstName, String lastName, String email, String semester, String course, String note) {
        this.firstName= firstName;
        this.lastName = lastName;
        this.email = email ;
        this.semester= semester ;
        this.course= course ;
        this.note= note ;
    }

    @Override
    public String toString() {
        return "PersonFormVO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", semester='" + semester + '\'' +
                ", course='" + course + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

}
