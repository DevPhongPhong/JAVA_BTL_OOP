package com.group5.btl.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"Classes\"")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT IDENTITY(1,1)", nullable = false)
    @Getter
    @Setter
    private int ID;

    @Column(name = "CourseID", length = 10, columnDefinition = "NVARCHAR(10)", nullable = false)
    @Getter
    @Setter
    private String CourseID;

    @Column(name = "CourseName", columnDefinition = "NTEXT", nullable = false)
    @Getter
    @Setter
    private String CourseName;

    @Column(name = "StudyGroup", columnDefinition = "TINYINT", nullable = false)
    @Getter
    @Setter
    private short StudyGroup;

    @Column(name = "PracticeGroup", columnDefinition = "TINYINT", nullable = false)
    @Getter
    @Setter
    private short PracticeGroup;

    @Column(name = "ClassCode", length = 10, columnDefinition = "NVARCHAR(10)", nullable = false)
    @Getter
    @Setter
    private String ClassCode;

    @Column(name = "CategoryID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int CategoryID;

    @Column(name = "Status", columnDefinition = "BIT", nullable = false)
    @Getter
    @Setter
    private boolean Status;

}

