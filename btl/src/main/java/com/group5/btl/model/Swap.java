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
@Table(name = "swaps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Swap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Student userId;

<<<<<<< HEAD
    @Column(name = "CreatedDate", columnDefinition = " DATETIME",nullable = false)
    private Timestamp CreatedDate;
=======
    @Column(name = "created_date")
    private Timestamp createdDate;
>>>>>>> login

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseId;
    
<<<<<<< HEAD
    @OneToMany(mappedBy = "SwapID",fetch = FetchType.LAZY)
    private List<SwapWish> ListSwapWish;

    @OneToMany(mappedBy = "SwapID",fetch = FetchType.LAZY)
    private List<JoinSwap> ListJoinSwap;
=======
    @OneToMany(mappedBy = "swapId")
    private List<SwapWish> listSwapWishs;

    @OneToMany(mappedBy = "swapId")
    private List<JoinSwap> listJoinSwaps;
>>>>>>> login
}
