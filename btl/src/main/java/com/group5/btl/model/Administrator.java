package com.group5.btl.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Administrators")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Administrator extends User{

    @Column(name = "CreatedDate",columnDefinition = "DATETIME",nullable = false)
    private Timestamp CreatedDate;

    @Column(name = "CreatedByUserID",columnDefinition = "INT",nullable = true)
    private int CreatedByUserID;
}
