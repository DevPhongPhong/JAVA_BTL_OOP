package com.group5.btl.service;

import com.group5.btl.model.Course;

public interface CourseService {
    Course getById(int id);
    Course getByCodeAndPracticeAndStudy(String courseCode, Short practiceGroup, Short studyGroup);
}
