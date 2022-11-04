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

<<<<<<< HEAD
    @Column(name = "CourseCode", columnDefinition = "NVARCHAR(10)",nullable = false)
    private String CourseCode;

    @Column(name = "CourseName", columnDefinition = "NTEXT",nullable = false)
    private String CourseName;

    @Column(name = "StudyGroup", columnDefinition = "TINYINT",nullable = false)
    private short StudyGroup;

    @Column(name = "PracticeGroup", columnDefinition = "TINYINT",nullable = false)
    private short PracticeGroup;
=======
    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name", columnDefinition = ("nvarchar(255)"))
    private String courseName;

    @Column(name = "study_group")
    private Short studyGroup;

    @Column(name = "practice_group")
    private Short practiceGroup;
>>>>>>> login

    @OneToMany(mappedBy = "courseId",fetch = FetchType.LAZY)
    private List<Swap> listSwaps;
    
//    @OneToMany(mappedBy = "courseId",fetch = FetchType.LAZY)
//    private List<SwapWish> listsSwapWishs;
}
