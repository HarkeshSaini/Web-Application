package com.spring.security.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

public class CommanUtility {

	private static final String dir = File.separator;

	public static void userRole(HttpServletRequest request, Model model) {
		model.addAttribute("userRole", request.getSession().getAttribute("role"));
	}

	public static String uploadFile(MultipartFile file) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String pathUrl = String.valueOf(request.getServletContext().getRealPath("") + "resources" + dir + "admin" + dir + "image");
		try {
			if (!file.isEmpty()) {
				Path path = Paths.get(pathUrl + dir + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				return pathUrl + file.getOriginalFilename();
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String dateFormate(String dateTimeString) {
		if (dateTimeString != null && !dateTimeString.isEmpty()) {
			LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
			LocalDate dateOnly = dateTime.toLocalDate();
			return dateOnly.format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		return dateTimeString;
	}

}
