package com.group5.btl.model;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "UserID")
public class Student extends User {

    @Column(name = "StudentCode", columnDefinition = "NVARCHAR(10) NOT NULL")
    private String StudentCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SectorID", referencedColumnName = "SectorID")
    private Sector SectorID;

    @OneToMany(mappedBy = "CreatedByUserID",fetch = FetchType.LAZY)
    private List<Swap> ListSwap;
}
