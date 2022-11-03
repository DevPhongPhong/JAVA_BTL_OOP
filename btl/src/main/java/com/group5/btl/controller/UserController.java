//package com.group5.btl.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.group5.btl.model.User;
//import com.group5.btl.service.UserServiceImpl;
//
//@Controller
//@RequestMapping(path = "/user")
//public class UserController {
//
//    private UserServiceImpl _service;
//
//    public UserController(UserServiceImpl service) {
//        _service = service;
//    }
//
////    @GetMapping("index")
////    public String getAll() {
////        List<User> listUser = _service.findAll();
////        Optional<User> user = _service.findByID(1);
////        System.out.println("ListUser");
////        for (User u : listUser) {
////            System.out.println(u.getID() + " " + u.getName() + " " + u.getEmail());
////        }
////        System.out.println("User by ID");
////        System.out.println(user.get().getID() + " " + user.get().getName() + " " + user.get().getEmail());
////
////        return "user/index";
////    }
//
//}
