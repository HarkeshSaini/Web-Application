package com.spring.security.utility;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public class CommanUtility {

	public static void userRole(HttpServletRequest request, Model model) {
		model.addAttribute("userRole", request.getSession().getAttribute("role"));
	}
}
