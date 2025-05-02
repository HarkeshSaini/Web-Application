package com.spring.security.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.interfaces.WebSiteUserService;
import com.spring.security.request.DefaultInfoRequest;
import com.spring.security.request.WebSiteUserRequest;

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
		model.addAttribute("message", "success");
		return "index";
	}

	@GetMapping("/contact-us")
	public String contactUs(HttpServletRequest request, Model model) {
		model.addAttribute("message", "success");
		return "contactUs";
	}

	@GetMapping("/about-us")
	public String aboutUs(HttpServletRequest request, Model model) {
		return getDefaultPageDetails("about-us", request, model);
	}

	@GetMapping("/terms-and-conditions")
	public String termsAndConditions(HttpServletRequest request, Model model) {
		return getDefaultPageDetails("terms-and-conditions", request, model);
	}

	@GetMapping("/privacy-policy")
	public String privacyPolicy(HttpServletRequest request, Model model) {
		return getDefaultPageDetails("privacy-policy", request, model);
	}

	private String getDefaultPageDetails(String pageUrl, HttpServletRequest request, Model model) {
		try {
			List<DefaultInfoRequest> defaultDetail = defaultInfoService.findAllDefaultByStatusAndPageUrl(pageUrl);
			if (defaultDetail.isEmpty()) {
				model.addAttribute("message", "Page content not available.");
				throw new NotFoundException("Page content not available. " +pageUrl);
			} else {
				model.addAttribute("defaultDetail", defaultDetail);
			}
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage()+pageUrl);
		} catch (Exception e) {
			throw new NotFoundException(e.getLocalizedMessage()+pageUrl);
		}
		return "default"; // The page will render using "default" template
	}
	
	  
    /**
     * Handles the login page.
     * @param request the HttpServletRequest object
     * @param model the Model to add attributes
     * @return the view name for login page
     */
    @GetMapping("/login")
    public String webLogin(HttpServletRequest request, Model model) {
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
    
}
