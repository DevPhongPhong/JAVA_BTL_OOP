package com.group5.btl.service.user;

import com.group5.btl.model.User;

public interface UserService {
    User GetUserByID(int id);

    int InsertUser(User user);

    int UpdateUser(User user);

    boolean DeleteUserByID(int id);
}
