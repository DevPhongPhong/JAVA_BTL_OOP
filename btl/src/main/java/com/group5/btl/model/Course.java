package com.group5.btl.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_code",columnDefinition = "nvarchar(15)", length = 15, nullable = false)
    private String courseCode;

    @Column(name = "course_name", columnDefinition = "nvarchar(255)",length = 255, nullable = false)
    private String courseName;

    @Column(name = "study_group", nullable = false)
    private Short studyGroup;

    @Column(name = "practice_group", nullable = false)
    private Short practiceGroup;

    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY)
    private List<Swap> listSwaps;

    @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY)
    private List<SwapWish> listsSwapWishs;

	public Course(String courseCode, String courseName, Short studyGroup, Short practiceGroup) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.studyGroup = studyGroup;
		this.practiceGroup = practiceGroup;
	}
}
