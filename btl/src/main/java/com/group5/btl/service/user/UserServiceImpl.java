package com.group5.btl.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group5.btl.model.User;
import com.group5.btl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    UserRepository _userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        _userRepository = userRepository;
    }

    public List<User> findAll() {
        return _userRepository.findAll();
    }

    public Optional<User> findByID(int ID) {
        return _userRepository.findById(ID);
    }

    public User saveAndFlush(User entity) {
        return _userRepository.saveAndFlush(entity);
    }

    public User save(User entity) {
        return _userRepository.save(entity);
    }

    public void flush() {
        _userRepository.flush();
    }
}
