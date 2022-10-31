package com.btlptit.demo.model;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(columnNames = "course_id"))
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "course_id")
	private String courseId;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "course_group")
	private Integer courseGroup;
	
	@Column(name = "practice_group")
	private Integer practiceGroup;
	
	@OneToMany(mappedBy = "course")
	private List<SwapOffer> swapOffers;
}
