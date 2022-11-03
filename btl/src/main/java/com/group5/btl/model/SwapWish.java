package com.group5.btl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SwapWishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwapWish {

    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "SwapID",referencedColumnName = "SwapID")
    private Swap SwapID;

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    private Course CourseID;

}
