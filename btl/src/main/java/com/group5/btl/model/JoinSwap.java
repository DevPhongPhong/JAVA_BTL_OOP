package com.group5.btl.model;

import javax.persistence.CascadeType;
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
@Table(name = "JoinSwaps")
public class JoinSwap {

    @Column(name = "JoinSwapID", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Id
    private int JoinSwapID;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Student UserID;


    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SwapID", referencedColumnName = "SwapID")
    private Swap SwapID;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    private Course CourseID;
}
