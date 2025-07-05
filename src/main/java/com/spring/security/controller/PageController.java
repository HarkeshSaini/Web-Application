package com.spring.security.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.spring.security.request.WebSiteUserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.request.DefaultInfoRequest;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageController {

	private final DefaultInfoService defaultInfoService;
	private final WebSiteUserService userService;

	public PageController(DefaultInfoService defaultInfoService, WebSiteUserService userService) {
		this.defaultInfoService = defaultInfoService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		request.getSession().setAttribute("strValue", null);
		var list = defaultInfoService.findAllDefaultByStatusAndPageUrl("about-us");
		model.addAttribute("abotutList", list);
		model.addAttribute("message", "success");
		return "index";
	}

	@GetMapping("/contact-us")
	public String contactUs(Model model) {
		model.addAttribute("message", "success");
		return "contactUs";
	}
	
	@GetMapping("/faq")
	public String frequentlyAskedQuestions(Model model) {
		model.addAttribute("message", "success");
		return "faq";
	}

	@GetMapping("/about-us")
	public String aboutUs(HttpServletRequest request, Model model) {
		return getDefaultPageDetails("about-us", model);
	}

	@GetMapping("/terms-and-conditions")
	public String termsAndConditions(HttpServletRequest request, Model model) {
		return getDefaultPageDetails("terms-and-conditions", model);
	}

	@GetMapping("/privacy-policy")
	public String privacyPolicy(HttpServletRequest request, Model model) {
		return getDefaultPageDetails("privacy-policy", model);
	}

	private String getDefaultPageDetails(String pageUrl, Model model) {
		var allDefaultByStatusAndPageUrl = defaultInfoService.findAllDefaultByStatusAndPageUrl(pageUrl);
		List<DefaultInfoRequest> defaultDetail = Optional.ofNullable(allDefaultByStatusAndPageUrl).filter(details -> !details.isEmpty()).orElseThrow(() -> new NotFoundException("Page content not available: " + pageUrl));
		model.addAttribute("defaultDetail", defaultDetail);
		return "default";
	}

	@GetMapping("/login")
	public String webLogin(Model model) {
		model.addAttribute("message", "Welcome back! Please sign in.");
		return "webUser/login";
	}

	@GetMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute("message", "Welcome back! Please sign in.");
		return "webUser/signUp";
	}

	@PostMapping("/sign-up")
	public String createNewUserForWeb(WebSiteUserRequest request, MultipartFile file, Model model) {
		Map<String, String> messString = userService.createNewUserForWeb(request, file);
		model.addAttribute("message", messString.get("message"));
		model.addAttribute("Password", messString.get("Password"));
		return "webUser/signUp";
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword(Model model) {
		model.addAttribute("message", "Please provide the required details and then click on Submit.");
		return "webUser/forPass";
	}

	@PostMapping("/forgotPassword")
	public String forgotPasswordRet(WebSiteUserRequest request, Model model) {
		String password = userService.forgotPassword(request);
		model.addAttribute("message", "Your forgot password: " + password);
		return "webUser/forPass";
	}
}