package com.group5.btl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.Course;
import com.group5.btl.model.Student;
import com.group5.btl.model.Swap;

@Repository
public interface SwapRepository extends JpaRepository<Swap, Integer> {
    List<Swap> findAll();
    List<Swap> findByCourseId(Course courseId);
    List<Swap> findByUserId(Student userId);
}
