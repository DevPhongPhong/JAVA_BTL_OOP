package com.group5.btl.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID", columnDefinition = "INT")
    @Getter
    @Setter
    private int CourseID;

    @Column(name = "CourseCode", columnDefinition = "NVARCHAR(10) NOT NULL")
    @Getter
    @Setter
    private String CourseCode;


    
    @Column(name = "CourseName", columnDefinition = "NTEXT NOT NULL")
    @Getter
    @Setter
    private String CourseName;

    @Column(name = "StudyGroup", columnDefinition = "TINYINT NOT NULL")
    @Getter
    @Setter
    private short StudyGroup;

    @Column(name = "PracticeGroup", columnDefinition = "TINYINT NOT NULL")
    @Getter
    @Setter
    private short PracticeGroup;

    @Getter
    @Setter
    @OneToMany(mappedBy = "CourseID",fetch = FetchType.LAZY)
    private List<Swap> ListSwap;
}
