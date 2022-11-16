package com.group5.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.model.Student;
import com.group5.btl.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public StudentRepository _sp;

    @Override
    public Student GetById(int id) {
        return _sp.findById(id).get();
    }

}
