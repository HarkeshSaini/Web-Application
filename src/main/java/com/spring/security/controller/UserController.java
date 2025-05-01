package com.spring.security.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.exception.GlobalExceptionHandler;
import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.request.WebSiteUserRequest;
import com.spring.security.utility.CommonUtility;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final WebSiteUserService userService;

    public UserController(WebSiteUserService userService) {
        this.userService = userService;
    }
    
    /**
     * Handles the login page.
     * @param request the HttpServletRequest object
     * @param model the Model to add attributes
     * @return the view name for login page
     */
    @GetMapping("/login")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("message", "Welcome back! Please sign in.");
        return "webUser/login";
    }

    /**
     * Displays the sign-up page.
     * @param request the HttpServletRequest object
     * @param model the Model to add attributes
     * @return the view name for sign-up page
     */
    @GetMapping("/sign-up")
    public String signUp(HttpServletRequest request, Model model) {
        model.addAttribute("message", "Welcome back! Please sign in.");
        return "webUser/signUp";
    }

    /**
     * Handles the sign-up process for new users.
     * @param request the WebSiteUserRequest object containing user details
     * @param file the file for the user's profile picture or document
     * @param model the Model to add attributes
     * @return the view name for sign-up page
     */
    @PostMapping("/sign-up")
    private String createNewUserForWeb(WebSiteUserRequest request,HttpServletRequest servletRequest, MultipartFile file, Model model) {
        try {
            Map<String, String> messString = userService.createNewUserForWeb(request, file);
            model.addAttribute("message", messString.get("message"));
            model.addAttribute("Password", messString.get("Password"));
        } catch (NotFoundException e) {
        	throw new NotFoundException(e.getLocalizedMessage());
        }
        return "webUser/signUp";
    }

    /**
     * Displays the forgot password page.
     * @param request the HttpServletRequest object
     * @param model the Model to add attributes
     * @return the view name for forgot password page
     */
    @GetMapping("/forgotPassword")
    private String forgotPassword(HttpServletRequest request, Model model) {
        model.addAttribute("message", "Please provide the required details and then click on Submit.");
        return "webUser/forPass";
    }

    /**
     * Handles the password recovery process.
     * @param request the WebSiteUserRequest object containing user details
     * @param model the Model to add attributes
     * @return the view name for forgot password page
     */
    @PostMapping("/forgotPassword")
    private String forgotPasswordRet(WebSiteUserRequest request,HttpServletRequest servletRequest, Model model) {
        try {
            String password = userService.forgotPassword(request);
            model.addAttribute("message", "Your forgot password:- " + password);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getLocalizedMessage());
        }
        return "webUser/forPass";
    }
    
    /**
     * Displays the user dashboard.
     * @param request the HttpServletRequest object
     * @param model the Model to add attributes
     * @return the view name for the user dashboard
     */
    @GetMapping("/user/dashboard")
    private String dashboard(HttpServletRequest request, Model model) {
        model.addAttribute("message", "User Panel – Welcome");
        CommonUtility.userRole(request, model);
        return "webUser/dashboard";
    }

}
