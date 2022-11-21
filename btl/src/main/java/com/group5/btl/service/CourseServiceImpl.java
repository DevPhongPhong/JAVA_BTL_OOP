package com.group5.btl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.model.Course;
import com.group5.btl.repository.CourseRepository;

import net.bytebuddy.asm.Advice.Return;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository _courseRepository;

    @Override
    public Course getById(Integer id) {
    	return _courseRepository.findById(id).get();
    }

    @Override
    public List<Course> getByCodeAndPracticeAndStudy(String courseCode, Short practiceGroup, Short studyGroup) {
        if (practiceGroup == 0 && studyGroup == 0)
            return _courseRepository.findByCourseCode(courseCode);
        else if (practiceGroup == 0) {
            return _courseRepository.findByCourseCodeAndStudyGroup(courseCode,studyGroup);
        } else if (studyGroup == 0) {
            return _courseRepository.findByCourseCodeAndPracticeGroup(courseCode, practiceGroup);
        }
        var res = new ArrayList<Course>();
        res.add(_courseRepository.findByCourseCodeAndPracticeGroupAndStudyGroup(courseCode, practiceGroup, studyGroup));
        return res;
    }

}
