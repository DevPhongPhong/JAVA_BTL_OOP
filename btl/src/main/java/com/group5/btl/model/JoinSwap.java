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
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "joinswaps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinSwap {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
//    @NotNull
    private Student userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "swap_id")
//    @NotNull
    private Swap swapId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
//    @NotNull
    private Course courseId;
}
