package com.group5.btl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SwapWishes")
public class SwapWish {

    @Column(name = "ID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Id
    private int ID;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "SwapID",referencedColumnName = "SwapID")
    private Swap SwapID;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    private Course CourseID;

}
