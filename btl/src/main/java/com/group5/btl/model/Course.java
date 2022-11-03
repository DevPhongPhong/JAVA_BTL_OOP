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
    @Column(name = "CourseID", columnDefinition = "INT")
    private int CourseID;

    @Column(name = "CourseCode", columnDefinition = "NVARCHAR(10) NOT NULL")
    private String CourseCode;

    @Column(name = "CourseName", columnDefinition = "NTEXT NOT NULL")
    private String CourseName;

    @Column(name = "StudyGroup", columnDefinition = "TINYINT NOT NULL")
    private short StudyGroup;

    @Column(name = "PracticeGroup", columnDefinition = "TINYINT NOT NULL")
    private short PracticeGroup;

    @OneToMany(mappedBy = "CourseID",fetch = FetchType.LAZY)
    private List<Swap> ListSwap;
}
