package com.group5.btl.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.UserRegistrationDto;
import com.group5.btl.model.Role;
import com.group5.btl.model.User;
import com.group5.btl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserSevice{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User save(UserRegistrationDto reg) {
		User user = new User(reg.getName(), reg.getEmail(), reg.getPhoneNumber(), passwordEncoder.encode(reg.getPassword()), roleService.findByName("USER"));
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password!");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(Arrays.asList(user.getRoleId())));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
