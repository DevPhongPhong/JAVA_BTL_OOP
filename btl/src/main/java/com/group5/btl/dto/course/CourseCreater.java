package com.group5.btl.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseCreater {
	private String courseCode;
	private String courseName;
	private Short studyGroup;
	private Short practiceGroup;
}
