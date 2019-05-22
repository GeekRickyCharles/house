package com.rickycharles.house.controller;

import com.rickycharles.house.common.model.User;
import com.rickycharles.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
