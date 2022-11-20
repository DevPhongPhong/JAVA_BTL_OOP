package com.group5.btl.service;

import java.util.List;

import javax.transaction.Transactional;

import com.group5.btl.model.Course;

public interface CourseService {
    Course getById(Integer id);
    List<Course> getByCodeAndPracticeAndStudy(String courseCode, Short practiceGroup, Short studyGroup);
}
