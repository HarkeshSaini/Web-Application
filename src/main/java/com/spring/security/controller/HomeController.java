package com.spring.security.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.security.exception.GlobalExceptionHandler;
import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.request.DefaultInfoRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    private final DefaultInfoService defaultInfoService;

    public HomeController(DefaultInfoService defaultInfoService) {
        this.defaultInfoService = defaultInfoService;
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
        try {
            List<DefaultInfoRequest> defaultDetail = defaultInfoService.findAllDefaultByStatusAndPageUrl(pageUrl);
            if (defaultDetail.isEmpty()) {
                model.addAttribute("message", "Page content not available.");
            } else {
                model.addAttribute("defaultDetail", defaultDetail);
            }
        } catch (NotFoundException e) {
            logger.error("Error fetching details for '{}': {}", pageUrl, e.getMessage());
            GlobalExceptionHandler.handleNotFoundException(e);
            model.addAttribute("message", "Page not found.");
        } catch (Exception e) {
            logger.error("Unexpected error for '{}': {}", pageUrl, e.getMessage());
            model.addAttribute("message", "An unexpected error occurred.");
        }
        return "default"; // The page will render using "default" template
    }
}
