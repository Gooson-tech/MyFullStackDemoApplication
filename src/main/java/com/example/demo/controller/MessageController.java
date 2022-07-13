package com.example.demo.controller;
import com.example.demo.Course;
import com.example.demo.CourseRepository;
import com.example.demo.StudentRegistration;
import com.example.demo.StudentRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    StudentRegistrationRepository studentRepository;
    CourseRepository courseRepository;

    @PostMapping("/sendRegistration")
    public ResponseEntity<StudentRegistration> sendRegistration(@Valid @RequestBody StudentRegistration student)
    {

        var search=courseRepository.findCourseByNameIgnoreCase(student.getCourse());
        if (search.isEmpty())
        {
            student.setCourse("");
            return new ResponseEntity<StudentRegistration>(student, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<StudentRegistration>(student, HttpStatus.OK);
        }
    }

    //On Launch, Get databases and insert the dummy data
    @Bean
    public CommandLineRunner run (StudentRegistrationRepository studentRep, CourseRepository courseRep){
        return (args->{
            studentRepository=studentRep;
            courseRepository=courseRep;
            insertDummyData(studentRep,courseRep);
        });
    }
    private void insertDummyData(StudentRegistrationRepository studentRep, CourseRepository courseRep){

        //only inserts if dummyData is not found
        if (studentRepository.findStudentRegistrationByFirstNameIgnoreCase("bill").isEmpty()) {
            studentRep.save(new StudentRegistration("Bill", "Testerson", "Bill@gmail.com", "spring", "MathI", "Can't wait!"));
            studentRep.save(new StudentRegistration("Grayson", "Cordell", "glc3k@mtmail.mtsu.edu", "spring", "Algebra I", "Can't wait!"));
            studentRep.save(new StudentRegistration("Bill", "Testerson", "Bill@yahoo.com", "spring", "MathI", "Can't wait!"));
            studentRep.save(new StudentRegistration("Bob", "TheBuilder", "Bill@gmail.com", "spring", "MathI", "Can't wait!"));

            courseRep.save(new Course("Algebra I", "1", 3080, "spring",3.0f));
            courseRep.save(new Course("Algebra II", "2", 3090, "spring",4.0f));
            courseRep.save(new Course("Economics I", "2", 3223,"spring", 2.0f));
            courseRep.save(new Course("Earth science", "1", 1150, "spring", 3.0f));

            courseRep.save(new Course("American literature", "1", 1150, "summer", 3.0f));
            courseRep.save(new Course("Life science", "1", 3333, "summer", 3.0f));
            courseRep.save(new Course( "Comparative literature", "1", 1150, "summer", 3.0f));
            //"French I" is full (not in database/removed)
            //"Botany" is full (not in database/removed)

        }
        else{
            System.out.println(studentRep);
            System.out.println(courseRep);
        }
    }

}

