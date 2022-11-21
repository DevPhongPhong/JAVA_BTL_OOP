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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(columnDefinition = "nvarchar(100) unique",nullable = false)
    private String sectorCode;
    
    @Column(columnDefinition = "nvarchar(100)",nullable = false)
    private String name;

    @OneToMany(mappedBy = "sectorID",fetch = FetchType.LAZY)
    private List<Student> listStudents;

	public Sector(String sectorCode, String name) {
		super();
		this.sectorCode = sectorCode;
		this.name = name;
	}
}
