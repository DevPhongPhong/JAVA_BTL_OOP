package com.group5.btl.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.hash.Hashing;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT")
    @Getter
    @Setter
    private int ID;

    @Column(name = "Name", columnDefinition = "NVARCHAR(30) NOT NULL")
    @Getter
    @Setter
    private String Name;

    @Column(name = "Email", columnDefinition = "NVARCHAR(30) NOT NULL UNIQUE")
    @Getter
    @Setter
    private String Email;

    @Column(name = "PhoneNumber", columnDefinition = "NVARCHAR(10) NOT NULL")
    @Getter
    @Setter
    public String PhoneNumber;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID")
    private Role RoleID;

    @Column(name = "Username", columnDefinition = " NVARCHAR(30) NOT NULL",unique = true)
    @Getter
    @Setter
    private String Username;

    @Column(name = "Password", columnDefinition = " NVARCHAR(64) NOT NULL")
    @Getter
    private String Password;

    public void setPassword(String password) {
        this.Password = Hashing.sha256().hashString(password, null).toString();
    }
}
