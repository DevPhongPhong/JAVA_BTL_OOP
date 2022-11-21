package com.group5.btl.service;

import com.group5.btl.dto.UserRegistrationDto;
import com.group5.btl.model.Student;
import com.group5.btl.dto.swap.SwapPreview;

public interface StudentService {
	Student save(UserRegistrationDto reg);
	Student findByEmail(String email);
    Student GetById(int id);
}
