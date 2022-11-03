package com.group5.btl.model;

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

    @Column(name = "CreatedDate",columnDefinition = "DATETIME NOT NULL")
    private String CreatedDate;

    @Column(name = "CreatedByUserID",columnDefinition = "INT")
    private Integer CreatedByUserID;
}
