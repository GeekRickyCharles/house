package com.rickycharles.house.controller;

import com.rickycharles.house.common.model.User;
import com.rickycharles.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HelloController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("hello")
    public String hello(ModelMap modelMap){
        List<User> users = userService.getUsers();
        User user = users.get(0);
        modelMap.put("user", user);
        return "hello";//返回freemarker的文件名称
    }

    @RequestMapping(value = "index")
    public String index(){
        return "homepage/index";
    }
}
