package com.group5.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.UserRegistrationDto;
import com.group5.btl.model.Sector;
import com.group5.btl.model.Student;
import com.group5.btl.repository.StudentRepository;
import com.group5.btl.service.sector.SectorService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private SectorService sectorService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Student save(UserRegistrationDto reg) {
		Student student = new Student(reg.getName(), reg.getEmail(), reg.getPhoneNumber(), passwordEncoder.encode(reg.getPassword()), roleService.findByName("STUDENT"), reg.getStudentCode(), sectorService.findByCode(reg.getSectorId()));
		return studentRepository.save(student);
	}

}
