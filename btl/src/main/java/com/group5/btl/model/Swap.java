package com.group5.btl.model;

import java.sql.Timestamp;
import java.util.List;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Swaps")
public class Swap {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SwapID", columnDefinition = "INT")
    private int SwapID;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "CreatedByUserID", referencedColumnName = "UserID")
    private Student CreatedByUserID;

    @Column(name = "CreatedDate", columnDefinition = " DATETIME NOT NULL")
    @Getter
    @Setter
    private Timestamp CreatedDate;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    private Course CourseID;

    @Getter
    @Setter
    @OneToMany(mappedBy = "SwapID")
    private List<SwapWish> ListSwapWish;

    @Getter
    @Setter
    @OneToMany(mappedBy = "SwapID")
    private List<JoinSwap> ListJoinSwap;
}
