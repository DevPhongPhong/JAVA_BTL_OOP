package com.group5.btl.service;

import java.util.List;

import com.group5.btl.model.Course;

public interface CourseService {
    Course getById(int id);
    List<Course> getByCodeAndPracticeAndStudy(String courseCode, Short practiceGroup, Short studyGroup);
}
