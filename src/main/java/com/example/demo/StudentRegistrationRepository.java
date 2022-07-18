package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRegistrationRepository extends CrudRepository<StudentRegistration,Long> {
    List<StudentRegistration> findStudentRegistrationByFirstNameAndLastNameIgnoreCase(String first, String last);
    List<StudentRegistration> findStudentRegistrationByFirstNameAndLastNameAndCourseIgnoreCase(String first, String last, String Course);
    List<StudentRegistration> findStudentRegistrationByFirstNameIgnoreCase(String str);

}
