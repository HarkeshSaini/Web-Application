package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.security.interfaces.WebSiteUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final WebSiteUserService userService;

	public UserController(WebSiteUserService userService) {
		this.userService = userService;
	}

	/**
	 * Displays the user dashboard.
	 * 
	 * @param request the HttpServletRequest object
	 * @param model   the Model to add attributes
	 * @return the view name for the user dashboard
	 */

	@GetMapping("/dashboard")
    public String showUserDashboard(Model model) {
        model.addAttribute("message", "Welcome to the admin dashboard");
        return "webUser/Dashboard";  
    }

}
