package com.spring.security.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

public class CommonUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtility.class);
    
	private static final String DIR = File.separator;

    public static void userRole(HttpServletRequest request, Model model) {
        if (request != null && model != null) {
            String role =(String) request.getSession().getAttribute("role");
            request.getSession().setAttribute("role", role);
            model.addAttribute("userRole", role);
        } else {
            LOGGER.warn("Request or Model is null in userRole method");
        }
    }

    public static String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            LOGGER.warn("Empty or null file received for upload");
            return null;
        }

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String pathUrl = request.getServletContext().getRealPath("") + "resources" + DIR + "admin" + DIR + "image";
            File directory = new File(pathUrl);
            if (!directory.exists() && !directory.mkdirs()) {
                LOGGER.error("Failed to create upload directory: {}", pathUrl);
                return null;
            }

            Path destination = Paths.get(pathUrl, file.getOriginalFilename());
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("File uploaded successfully to {}", destination);
           // return destination.toString(); for live server
            return file.getOriginalFilename();
        } catch (IOException e) {
            LOGGER.error("IOException during file upload: {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Unexpected error during file upload: {}", e.getMessage());
        }

        return null;
    }

    public static String dateFormate(String dateTimeString) {
        if (dateTimeString != null && !dateTimeString.isEmpty()) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
                LocalDate dateOnly = dateTime.toLocalDate();
                return dateOnly.format(DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                LOGGER.error("Invalid date format: {}", dateTimeString);
            }
        } else {
            LOGGER.warn("Provided date string is null or empty");
        }

        return dateTimeString;
    }
}
