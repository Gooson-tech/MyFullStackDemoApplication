package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findCourseByNameIgnoreCase(String str);
    List<Course> findCoursesBySemesterIgnoreCase(String str);
}
