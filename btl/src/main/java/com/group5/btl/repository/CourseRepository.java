package com.group5.btl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{

    List<Course> findByCourseCode(String courseCode);

    List<Course> findByCourseCodeAndPracticeGroup(String courseCode, Short practiceGroup);

    List<Course> findByCourseCodeAndStudyGroup(String courseCode, Short studyGroup);
    
    Course findByCourseCodeAndPracticeGroupAndStudyGroup(String courseCode, Short practiceGroup, Short studyGroup);
}
