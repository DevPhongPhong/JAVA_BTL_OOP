package com.group5.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.model.Course;
import com.group5.btl.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository _courseRepository;

    @Override
    public Course getById(int id) {
       return _courseRepository.findById(id).get();
    }
    
}