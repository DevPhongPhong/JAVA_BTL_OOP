package com.group5.btl.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Administrators")





public class Administrator extends User{

    @Column(name = "CreatedDate",columnDefinition = "DATETIME NOT NULL")
    @Getter
    @Setter
    private String CreatedDate;

    @Column(name = "CreatedByUserID",columnDefinition = "INT")
    @Getter
    @Setter
    private int CreatedByUserID;
}
