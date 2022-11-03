package com.group5.btl.service.course;

import org.springframework.stereotype.Service;

import com.group5.btl.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository _courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        super();
        _courseRepository = courseRepository;
    }

}
