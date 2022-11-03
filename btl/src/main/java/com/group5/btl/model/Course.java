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
@Table(name = "Course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    private Integer CourseID;

    @Column(name = "CourseCode")
    private String CourseCode;

    @Column(name = "CourseName")
    private String CourseName;

    @Column(name = "StudyGroup")
    private Short StudyGroup;

    @Column(name = "PracticeGroup")
    private Short PracticeGroup;

    @OneToMany(mappedBy = "CourseID",fetch = FetchType.LAZY)
    private List<Swap> ListSwap;
}
