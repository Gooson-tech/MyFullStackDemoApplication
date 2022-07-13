package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRegistrationRepository extends CrudRepository<StudentRegistration,Long> {
    List<StudentRegistration> findStudentRegistrationByFirstNameIgnoreCase(String str);

}
