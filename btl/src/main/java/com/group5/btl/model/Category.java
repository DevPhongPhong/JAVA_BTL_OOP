package com.group5.btl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT")
    @Getter
    @Setter
    public int ID;

    @Column(name = "Sector", length = 10, columnDefinition = "NVARCHAR(10)", nullable = false)
    @Getter
    @Setter
    public String Sector;

    @Column(name = "SectorName", columnDefinition = "NTEXT", nullable = false)
    @Getter
    @Setter
    public String SectorName;

    @Column(name = "Year", columnDefinition = "TINYINT", nullable = false)
    @Getter
    @Setter
    public short Year;

    @Column(name = "Term", columnDefinition = "TINYINT", nullable = false)
    @Getter
    @Setter
    public short Term;
}
