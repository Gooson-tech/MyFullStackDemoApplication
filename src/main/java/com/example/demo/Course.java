package com.example.demo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "courseRegistration")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Course implements Serializable {

    private String semester;
    private String name;
    private String section;
    private int number;
    private float credit;
    private int attending;
    private int maxStudent;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    public Course() {}

    public Long getId() {
        return id;
    }

    public Course(String name, String section, int number, String semester, float credit, int attending, int maxStudent) {
       this.name= name;
       this.section=section;
       this.number=number;
       this.credit=credit;
       this.semester=semester;
       this.attending = attending;
       this.maxStudent= maxStudent;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", number='" + number + '\'' +
                ", credit='" + credit + '\'' +
                ", id=" + id +
                '}';
    }


    public int getAttending() {
        return attending;
    }

    public void setAttending(int attending) {
        this.attending = attending;
    }
    public void addAttending(int attending) {
        this.attending += attending;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }
}
