

package com.group5.btl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group5.btl.service.user.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private UserService _userService;
    public UserController(UserService userService){
        _userService = userService;
    }

    @GetMapping("create")
    public String Create(){
        var user = _userService.GetUserByID(2);
        return "";
    }
}
