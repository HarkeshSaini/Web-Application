package com.spring.security.utility;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

public class CommanUtility {

	private static final String dir = File.separator;

	private static final HashMap<String, Object> hashMap = new HashMap<>();

	public static void userRole(HttpServletRequest request, Model model) {
		model.addAttribute("userRole", request.getSession().getAttribute("role"));
	}

	public static String uploadFile(MultipartFile file) {
		String pathUrl = fetchFileLocation();
		HashMap<String, Object> hashMap = uploadFileOnServer(file, pathUrl);
		boolean status = (boolean) hashMap.get("status");
		if (status) {
			return (String) hashMap.get("fileLocation");
		}
		return null;
	}

	private static HashMap<String, Object> uploadFileOnServer(MultipartFile file, String pathUrl) {
		if (file.getSize() != 0) {
			try {
				if (checkFileExitOrNot(pathUrl + dir, file)) {
					Path path = Paths.get(pathUrl + dir + file.getOriginalFilename());
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					hashMap.put("messages", "File successfully submitted");
					hashMap.put("status", true);
					hashMap.put("fileLocation", fetchFileLocation() + dir + file.getOriginalFilename());
					hashMap.put("file_name", file.getOriginalFilename());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				hashMap.put("fileLocation", fetchFileLocation() + file.getOriginalFilename());
				hashMap.put("messages", "Server error:- " + e.getMessage());
				hashMap.put("status", false);
				hashMap.put("file_name", file.getOriginalFilename());
			}
		}
		return hashMap;
	}

	private static boolean checkFileExitOrNot(String fileLocation, MultipartFile file) {
		Path path = Paths.get(fileLocation + file.getOriginalFilename());
		if (Files.exists(path)) {
			hashMap.put("messages", "Please change the file name Because already exits same file");
			hashMap.put("status", false);
			hashMap.put("file_name", file.getOriginalFilename());
			return false;
		}
		return true;
	}

	private static String fetchFileLocation() {
		final URL location = CommanUtility.class.getProtectionDomain().getCodeSource().getLocation();
		String currentDir;
		try {
			File file = new File(location.toURI());
			currentDir = file.getParentFile().getParent();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		String resourcePath = currentDir + dir + "src" + dir + "main" + dir + "resources" + dir + "admin_file" + dir;
		File resourceFile = new File(resourcePath);
		return resourceFile.getAbsoluteFile().getPath();
	}

}
