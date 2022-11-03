package com.group5.btl.model;

import java.sql.Timestamp;
import java.util.List;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Swaps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Swap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SwapID", columnDefinition = "INT")
    private int SwapID;

    @ManyToOne
    @JoinColumn(name = "CreatedByUserID", referencedColumnName = "UserID")
    private Student CreatedByUserID;

    @Column(name = "CreatedDate", columnDefinition = " DATETIME",nullable = false)
    private Timestamp CreatedDate;

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    private Course CourseID;
    
    @OneToMany(mappedBy = "SwapID",fetch = FetchType.LAZY)
    private List<SwapWish> ListSwapWish;

    @OneToMany(mappedBy = "SwapID",fetch = FetchType.LAZY)
    private List<JoinSwap> ListJoinSwap;
}
