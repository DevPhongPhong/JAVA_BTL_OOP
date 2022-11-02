package com.group5.btl.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Sectors")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SectorID", columnDefinition = "INT")
    @Getter
    @Setter
    private int SectorID;

    @Column(name = "Name", columnDefinition = "NTEXT NOT NULL")
    @Getter
    @Setter
    private String SectorName;

    @Getter
    @Setter
    @OneToMany(mappedBy = "SectorID",fetch = FetchType.LAZY)
    private List<Student> ListStudent;
}
