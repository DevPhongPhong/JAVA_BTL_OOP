package com.group5.btl.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "UserID", columnDefinition = " INT", nullable = false)
    @Getter
    @Setter
    private int UserID;

    @Column(name = "ClassCode", length = 15, columnDefinition = " NVARCHAR(15)", nullable = false)
    @Getter
    @Setter
    private String ClassCode;

    @Column(name = "Sector", length = 10, columnDefinition = " NVARCHAR(10)", nullable = false)
    @Getter
    @Setter
    private String Sector;

    @Column(name = "StudentCode", length = 15, columnDefinition = " Nvarchar(15)", nullable = false)
    @Getter
    @Setter
    private String StudentCode;
    
}
