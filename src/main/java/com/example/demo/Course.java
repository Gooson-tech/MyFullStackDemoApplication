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
    private float credits;
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

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    public Course() {
    }

    public Long getId() {
        return id;
    }

    public Course(String name, String section, int number, String semester, float credits) {
       this.name= name;
       this.section=section;
       this.number=number;
       this.credits=credits;
       this.semester=semester;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", number='" + number + '\'' +
                ", credits='" + credits + '\'' +
                ", id=" + id +
                '}';
    }


}
