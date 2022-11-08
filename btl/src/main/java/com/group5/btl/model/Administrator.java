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
@Table(name = "administrator")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Administrator extends User{

    @Column(name = "created_date",nullable = false)
    private Timestamp createdDate;

    @Column(name = "created_by_user_id",nullable = false)
    private Integer createdByUserID;
}
