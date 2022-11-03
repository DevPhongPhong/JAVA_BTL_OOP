package com.group5.btl.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group5.btl.model.User;
import com.group5.btl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    UserRepository _userRepository;

    public UserServiceImpl (UserRepository userRepository){
        _userRepository = userRepository;
    }

    @Override
    public User GetUserByID(int id) {
        return _userRepository.findById(id).get();
    }

    @Override
    public int InsertUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int UpdateUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean DeleteUserByID(int id) {
        // TODO Auto-generated method stub
        return false;
    }
}
