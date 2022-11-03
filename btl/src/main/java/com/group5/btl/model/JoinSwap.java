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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JoinSwaps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinSwap {

    @Column(name = "JoinSwapID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer JoinSwapID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Student UserID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SwapID", referencedColumnName = "SwapID")
    private Swap SwapID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    private Course CourseID;
}
