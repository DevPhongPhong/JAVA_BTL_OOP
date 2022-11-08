package com.group5.btl.model;

import javax.persistence.CascadeType;
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
@Table(name = "joinswaps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinSwap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",nullable = false)
    private Student userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "swap_wish_id",nullable = false)
    private SwapWish swapWish;
}
