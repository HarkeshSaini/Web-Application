package com.spring.security.controller;

import com.spring.security.service.UserRegistrationServices;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    public UserRegistrationServices userRegistrationServices;

    @GetMapping({"/"})
    private String loginPage(){
        System.out.println("Start application");
        return "login";
    }
}
