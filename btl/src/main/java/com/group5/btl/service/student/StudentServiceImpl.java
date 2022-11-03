package com.group5.btl.service.student;

import org.springframework.stereotype.Service;

import com.group5.btl.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository _studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        _studentRepository = studentRepository;
    }

}