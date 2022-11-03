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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Students")
@PrimaryKeyJoinColumn(name = "UserID")
public class Student extends User {

    @Column(name = "StudentCode", columnDefinition = "NVARCHAR(10) NOT NULL")
    @Getter
    @Setter
    private String StudentCode;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SectorID", referencedColumnName = "SectorID")
    private Sector SectorID;

    @Getter
    @Setter
    @OneToMany(mappedBy = "CreatedByUserID",fetch = FetchType.LAZY)
    private List<Swap> ListSwap;
}
