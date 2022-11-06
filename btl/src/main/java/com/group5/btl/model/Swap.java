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
import javax.validation.constraints.NotNull;

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
//    @NotNull
    private Student userId;

    @Column(name = "created_date")
    @NotNull
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
//    @NotNull
    private Course courseId;
    
    @OneToMany(mappedBy = "swapId")
    private List<SwapWish> listSwapWishs;

    @OneToMany(mappedBy = "swapId")
    private List<JoinSwap> listJoinSwaps;
}
