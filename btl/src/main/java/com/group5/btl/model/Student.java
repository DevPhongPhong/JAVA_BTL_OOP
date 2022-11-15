package com.group5.btl.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    @Column(name = "student_code",columnDefinition = "NVARCHAR(15)",length = 15, nullable = false)
    private String studentCode;

    @ManyToOne
    @JoinColumn(name = "sector_id",nullable = false)
    private Sector sectorID;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Swap> listSwaps;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<JoinSwap> listJoinSwaps;
}
