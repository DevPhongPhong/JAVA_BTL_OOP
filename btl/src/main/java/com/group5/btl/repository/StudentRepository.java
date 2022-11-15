package com.group5.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
//    Student save(Student student);
	Student findByEmail(String email);
}
