package com.group5.btl.controller.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.btl.dto.course.CourseInfo;
import com.group5.btl.model.Course;
import com.group5.btl.service.CourseService;


@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@PostMapping("/add")
	public ResponseEntity addCourse(@RequestBody Course course) {
		
		return ResponseEntity.ok().body(course);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/{courseCode}/{studyGroup}/{practiceGroup}")
	public List<CourseInfo> getCourse(@PathVariable(name = "courseCode") String courseCode,
			@PathVariable(name = "studyGroup") Short studyGroup,
			@PathVariable(name = "practiceGroup") Short practiceGroup) {
		List<CourseInfo> res = new ArrayList<>();
		List<Course> list = courseService.getByCodeAndPracticeAndStudy(courseCode, practiceGroup, studyGroup);
    	for(Course xCourse : list) {
    		res.add(new CourseInfo(xCourse.getId(), xCourse.getCourseCode(), xCourse.getCourseName(), xCourse.getStudyGroup(), xCourse.getPracticeGroup()));
    	}
    	return res;
	}
}
