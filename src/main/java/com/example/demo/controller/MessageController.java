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
import java.util.Collections;

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
        //Currently course is sent as full string "Botany, Section: 1, Credits: 1" so we will just split for now
        var courseName= student.getCourse().split(",")[0];
        var courses= courseRepository.findCourseByNameIgnoreCase(courseName);
        var course = courseRepository.findCourseByNameIgnoreCase(courseName);
       // if (course==null){ student.setClassDoesntExistError(true);}

        //TODO -add check for class being full already, and maybe ID/Password
        boolean alreadyApplied   = !studentRepository.findStudentRegistrationByFirstNameAndLastNameAndCourseIgnoreCase(student.getFirstName(), student.getLastName(), student.getCourse()).isEmpty();
        if (alreadyApplied)
        {
           // student.setSetAlreadyAppliedError(true);
            return new ResponseEntity<StudentRegistration>(student, HttpStatus.OK);
        }
        else if ( course.get(0).getAttending() >= course.get(0).getMaxStudent() )
        {
            System.out.println("test");
            //TODO SEND that class is full!
        }
        else{
            //TODO ADD 1 TO CLASS RECORD
        }
        return new ResponseEntity<StudentRegistration>(student, HttpStatus.OK);
    }


    @GetMapping("/getClassesInSpring2022")
    public Course[] GetClassesInSpring() {
        var springClassesList=courseRepository.findCoursesBySemesterIgnoreCase("spring2022");
        return  springClassesList.toArray(new Course[0]);
    }
    @GetMapping("/getClassesInSummer2022")
    public Course[] GetClassesInSummer() {
        var summerClassesList=courseRepository.findCoursesBySemesterIgnoreCase("summer2022");
        return summerClassesList.toArray(new Course[0]);
    }
    @GetMapping("/getClassesInFall2022")
    public Course[] GetClassesInFall() {
        var fallClassesList=courseRepository.findCoursesBySemesterIgnoreCase("fall2022");
        return fallClassesList.toArray(new Course[0]);
    }

    //On Launch, Get databases and insert the dummy data
    @Bean
    public CommandLineRunner run (StudentRegistrationRepository studentRep, CourseRepository courseRep)
    {
        return (args->
        {
            studentRepository=studentRep;
            courseRepository=courseRep;
            insertDummyData(studentRep,courseRep);
        });
    }
    private void insertDummyData(StudentRegistrationRepository studentRep, CourseRepository courseRep)
    {

        //only inserts if dummyData is not found
        if (studentRepository.findStudentRegistrationByFirstNameIgnoreCase("DummyData").isEmpty())
        {
            studentRep.save(new StudentRegistration("DummyData", "Dummyson", "DummyData@gmail.com", "spring2022", "MathI", "Can't wait!"));
            studentRep.save(new StudentRegistration("Grayson", "Cordell", "glc3k@mtmail.mtsu.edu", "spring2022", "Algebra I", "Can't wait!"));
            studentRep.save(new StudentRegistration("Bob", "TheBuilder", "Bob@yahoo.com", "spring2022", "MathI", "Can't wait!"));

            courseRep.save(new Course("Algebra I", "1", 3081, "spring2022",3.0f, 18,21));
            courseRep.save(new Course("Algebra II", "2", 1091, "spring2022",4.0f, 18,21));
            courseRep.save(new Course("Economics I", "2", 3223,"spring2022", 2.0f, 21,33));
            courseRep.save(new Course("Earth science", "1", 1160, "spring2022", 3.0f, 1,21));

            courseRep.save(new Course("American literature", "1", 1150, "summer2022", 3.0f, 2,21));
            courseRep.save(new Course("Life science", "1", 3333, "summer2022", 3.0f, 3,21));
            courseRep.save(new Course( "Comparative literature", "1", 1150, "summer2022", 2.0f, 20,21));
            courseRep.save(new Course( "Sociology of Fatherhood", "2", 1150, "summer2022", 3.0f, 4,21));

            courseRep.save(new Course("Distributed Computing", "1", 2180, "fall2022",3.0f, 4,21));
            courseRep.save(new Course("Plasma Astrophysics", "2", 5090, "fall2022",4.0f, 21,22));
            courseRep.save(new Course("Kinesiology", "2", 1090, "fall2022",3.0f, 4,21));
            courseRep.save(new Course("Cartography", "1", 1020, "fall2022",2.0f, 4,33));
            courseRep.save(new Course("Community Practice", "2", 1122,"fall2022", 1.0f, 6,21));

            //full
            courseRep.save(new Course("FrenchI", "2", 1122,"fall2022", 1.0f, 21,21));
            courseRep.save(new Course("Botany", "1", 1122,"fall2022", 1.0f, 21,21));
            //"French I" is full (not in database/removed)
            //"Botany" is full (not in database/removed)
        }
        else
        {
            System.out.println(studentRep);
            System.out.println(courseRep);
        }
    }

}

