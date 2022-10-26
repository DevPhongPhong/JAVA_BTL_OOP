package com.group5.btl.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "ID", columnDefinition = " INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int ID;

    @Column(name = "Dob", columnDefinition = "DATE", nullable = false)
    @Getter
    @Setter
    private Date Dob;

    @Column(name = "Email", length = 30, columnDefinition = "NVARCHAR(30)", unique = true, nullable = false)
    @Getter
    @Setter
    private String Email;

    @Column(name = "RoleID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int RoleID;

    @Column(name = "Status", columnDefinition = "BIT", nullable = false)
    @Getter
    @Setter
    private boolean Status;

    @Column(name = "Name", length = 30, columnDefinition = "NVARCHAR(30)", nullable = false)
    @Getter
    @Setter
    private String Name;

    @Column(name = "MainImage", columnDefinition = "NTEXT", nullable = false)
    @Getter
    @Setter
    private String MainImage;

    @Column(name = "CreatedDate", columnDefinition = "DATETIME", nullable = false)
    @Getter
    @Setter
    private Timestamp CreatedDate;

    @Column(name = "CreatedByUserID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int CreatedByUserID;

    @Column(name = "LastestModifiedDate", columnDefinition = "DATETIME", nullable = false)
    @Getter
    @Setter
    private Timestamp LastestModifiedDate;

    @Column(name = "LastestModifiedByUserID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int LastestModifiedByUserID;

}
