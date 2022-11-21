package com.group5.btl.model;

import java.util.List;
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
@Table(name = "swapWishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwapWish {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "swap_id",nullable = false)
    private Swap swapId;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course courseId;

    @OneToMany(mappedBy = "swapWish", fetch = FetchType.LAZY)
    private List<JoinSwap> listJoinSwaps;
}
