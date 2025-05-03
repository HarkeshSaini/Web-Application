package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.utility.CommonUtility;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final WebSiteUserService userService;

    public UserController(WebSiteUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    private String dashboard(HttpServletRequest request, Model model) {
        model.addAttribute("message", "User Panel – Welcome");
        CommonUtility.userRole(request, model);   
        return "webUser/Dashboard";   
    }
}
