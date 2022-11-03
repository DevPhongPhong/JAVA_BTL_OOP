package com.group5.btl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT")
    private Integer ID;

    @Column(name = "Name", columnDefinition = "NVARCHAR(30) NOT NULL")
    private String Name;

    @Column(name = "Email", columnDefinition = "NVARCHAR(30) NOT NULL UNIQUE")
    private String Email;

    @Column(name = "PhoneNumber", columnDefinition = "NVARCHAR(10) NOT NULL")
    public String PhoneNumber;

    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID")
    private Role RoleID;

    @Column(name = "Username", columnDefinition = " NVARCHAR(30) NOT NULL", unique = true)
    private String Username;

    @Column(name = "Password", columnDefinition = " NVARCHAR(64) NOT NULL")
    private String Password;
}
