package com.spring.security.controller;

import java.sql.Timestamp;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.interfaces.BlogInfoService;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.interfaces.ContactInfoService;
import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.interfaces.UserInfoService;
import com.spring.security.request.BlogInfoRequest;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;
import com.spring.security.request.ContactInfoRequest;
import com.spring.security.request.DefaultInfoRequest;
import com.spring.security.request.UserInfoRequest;
import com.spring.security.utility.CommanUtility;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final BlogInfoService blogService;

	private final UserInfoService registerService;
	
	private final CategoryInfoService categoryService;
	
	private final DefaultInfoService defaultInfoService;

	private final ContactInfoService contactInfoService;

	public AdminController(UserInfoService registerService, ContactInfoService contactInfoService,
		BlogInfoService blogService, DefaultInfoService defaultInfoService, CategoryInfoService categoryService) {
		this.blogService = blogService;
		this.registerService = registerService;
		this.categoryService = categoryService;
		this.defaultInfoService = defaultInfoService;
		this.contactInfoService = contactInfoService;
	}

	@GetMapping
	public String dashBoardLogin(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Welcome to the admin login! Please sign in");
		CommanUtility.userRole(request, model);
		return "admin/login";
	}

	@GetMapping("/dashboard")
	private String dashboard(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Admin Panel – Welcome");
		CommanUtility.userRole(request, model);
		return "admin/dashboard";
	}

	@GetMapping("/getAllUser")
	private String getAllUser(HttpServletRequest request, Model model) {
		List<UserInfoRequest> userInfo = this.registerService.getAllUser();
		model.addAttribute("userInfo", userInfo);
		CommanUtility.userRole(request, model);
		model.addAttribute("message", "List of All Registered Users");
		return "admin/user/showUser";
	}

	@GetMapping("/addUser")
	private String addUser(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Create a new user with administrator privileges.");
		CommanUtility.userRole(request, model);
		return "admin/user/addUser";
	}

	@PostMapping("/addUser")
	private String addUser(UserInfoRequest userRequest, MultipartFile file, Model model, HttpServletRequest request) {
		userRequest.setCreateDate(new Timestamp(System.currentTimeMillis()));
		UserInfoRequest addUser = this.registerService.addUser(userRequest, file);
		CommanUtility.userRole(request, model);
		if (ObjectUtils.isEmpty(addUser)) {
			model.addAttribute("message", "User already exists!");
			return "admin/user/addUser";
		}
		model.addAttribute("message", "User created successfully!");
		return "admin/user/addUser";
	}

	@GetMapping("/editUserInfo/{id}")
	private String editUserInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		UserInfoRequest userById = this.registerService.getUserById(id);
		CommanUtility.userRole(request, model);
		model.addAttribute("id", id);
		model.addAttribute("command", userById);
		model.addAttribute("message", "Update User Information");
		return "admin/user/editUser";
	}

	@PutMapping("/editUserInfo/{id}")
	private String editUserInfo(@PathVariable String id, UserInfoRequest infoRequest, MultipartFile file, Model model,
			HttpServletRequest request) {
		UserInfoRequest userById = this.registerService.updateUser(id, file, infoRequest);
		CommanUtility.userRole(request, model);
		if (ObjectUtils.isEmpty(userById)) {
			model.addAttribute("id", id);
			model.addAttribute("command", infoRequest);
			model.addAttribute("message", "User information could not be updated.");
			return "admin/user/editUser";
		}
		model.addAttribute("id", id);
		model.addAttribute("command", userById);
		model.addAttribute("message", "User details have been successfully updated.");
		return "admin/user/addUser";
	}

	@DeleteMapping("/deleteUserInfo/{id}")
	private String deleteUserInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		CommanUtility.userRole(request, model);
		this.registerService.deleteUserById(id);
		return "redirect:/admin/getAllUser";
	}

	/*
	 * =====================================Contact Info================================================
	 */

	@GetMapping("/showAllContactInfo")
	private String showAllContactInfo(HttpServletRequest request, Model model) {
		List<ContactInfoRequest> contactInfo = this.contactInfoService.showAllContactInfo();
		model.addAttribute("contactInfo", contactInfo);
		CommanUtility.userRole(request, model);
		model.addAttribute("message", "List of Users Who Submitted the Contact Us Form");
		return "admin/contactUs";
	}

	@DeleteMapping("/deleteContactUsInfo/{id}")
	private String deleteContactUsInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		CommanUtility.userRole(request, model);
		this.contactInfoService.deleteById(id);
		return "redirect:/admin/showAllContactInfo";
	}

	/*
	 * =======================================Blog Content==========================================
	 */

	@GetMapping("/getAllBlog")
	private String getAllBlog(HttpServletRequest request, Model model) {
		List<BlogInfoRequest> blogRequest = this.blogService.getAllBlog();
		model.addAttribute("blogRequest", blogRequest);
		CommanUtility.userRole(request, model);
		model.addAttribute("message", "List of All blog details");
		return "admin/blog/showBlog";
	}

	@GetMapping("/addBlog")
	private String addBlog(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Create a new Blog with administrator privileges.");
		CommanUtility.userRole(request, model);
		return "admin/blog/addBlog";
	}

	@PostMapping("/addBlog")
	private String addBlogs(BlogInfoRequest blogRequest, MultipartFile file, Model model, HttpServletRequest request) {
		blogRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
		BlogInfoRequest addBlog = this.blogService.addBlog(blogRequest, file);
		CommanUtility.userRole(request, model);
		if (ObjectUtils.isEmpty(addBlog)) {
			model.addAttribute("message", "Blog already exists!");
			return "admin/blog/addBlog";
		}
		model.addAttribute("message", "Blog created successfully!");
		return "admin/blog/addBlog";
	}

	@GetMapping("/editBlogInfo/{id}")
	private String editBlogInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		BlogInfoRequest blogById = this.blogService.getBlogById(id);
		CommanUtility.userRole(request, model);
		model.addAttribute("id", id);
		model.addAttribute("command", blogById);
		model.addAttribute("message", "Update Blog Information");
		return "admin/blog/editBlog";
	}

	@PutMapping("/editBlogInfo/{id}")
	private String editBlogInfos(@PathVariable String id, BlogInfoRequest infoRequest, MultipartFile file, Model model, HttpServletRequest request) {
		BlogInfoRequest blogById = this.blogService.updateBlog(id, file, infoRequest);
		CommanUtility.userRole(request, model);
		if (ObjectUtils.isEmpty(blogById)) {
			model.addAttribute("id", id);
			model.addAttribute("command", infoRequest);
			model.addAttribute("message", "Blog information could not be updated.");
			return "admin/blog/editBlog";
		}
		model.addAttribute("message", "Blog details have been successfully updated.");
		return "admin/blog/addBlog";
	}

	@DeleteMapping("/deleteBlogInfo/{id}")
	private String deleteBlogInfos(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		CommanUtility.userRole(request, model);
		this.blogService.deleteBlogById(id);
		return "redirect:/admin/getAllBlog";
	}

	/*
	 * =======================================Default Content==========================================
	 */

	@GetMapping("/getAllDefaultContant")
	private String getAllDefaultContant(HttpServletRequest request, Model model) {
		List<DefaultInfoRequest> defaultInfoRequests = this.defaultInfoService.getAllDefaultContant();
		model.addAttribute("defaultInfoRequests", defaultInfoRequests);
		CommanUtility.userRole(request, model);
		model.addAttribute("message", "List of All default Info details");
		return "admin/default/showDefault";
	}

	@GetMapping("/addDefault")
	private String addDefault(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Create a new Default with administrator privileges.");
		CommanUtility.userRole(request, model);
		return "admin/default/addDefault";
	}

	@PostMapping("/addDefault")
	private String addDefault(DefaultInfoRequest defaultRequest, MultipartFile file, Model model, HttpServletRequest request) {
		defaultRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
		DefaultInfoRequest addDefault = this.defaultInfoService.addDefault(defaultRequest, file);
		CommanUtility.userRole(request, model);
		if (ObjectUtils.isEmpty(addDefault)) {
			model.addAttribute("message", "Default already exists!");
			return "admin/default/addDefault";
		}
		model.addAttribute("message", "Default created successfully!");
		return "admin/default/addDefault";
	}

	@GetMapping("/editDefaultInfo/{id}")
	private String editDefaultInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		DefaultInfoRequest defaultById = this.defaultInfoService.getDefaultById(id);
		CommanUtility.userRole(request, model);
		model.addAttribute("id", id);
		model.addAttribute("command", defaultById);
		model.addAttribute("message", "Update Default Information");
		return "admin/default/editDefault";
	}

	@PutMapping("/editDefaultInfo/{id}")
	private String editDefaultInfos(@PathVariable String id, DefaultInfoRequest infoRequest, MultipartFile file, Model model, HttpServletRequest request) {
		DefaultInfoRequest defaultById = this.defaultInfoService.updateDefault(id, file, infoRequest);
		CommanUtility.userRole(request, model);
		if (ObjectUtils.isEmpty(defaultById)) {
			model.addAttribute("id", id);
			model.addAttribute("command", infoRequest);
			model.addAttribute("message", "Default information could not be updated.");
			return "admin/default/editDefault";
		}
		model.addAttribute("message", "Default details have been successfully updated.");
		return "admin/default/addDefault";
	}

	@DeleteMapping("/deleteDefaultInfo/{id}")
	private String deleteDefaultInfos(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		CommanUtility.userRole(request, model);
		this.defaultInfoService.deleteDefaultById(id);
		return "redirect:/admin/getAllDefaultContant";
	}
	
	
	/*
	 * =======================================Category Information Content==========================================
	 */
	

	@GetMapping("/getAllCategory")
	private String getAllCategory(HttpServletRequest request, Model model) {
		List<CategoryInfoRequest> infoRequests = this.categoryService.getAllCategoryContant();
		List<CategoryReq> allInfoCategory = categoryService.getAllInfoCategory();
		model.addAttribute("infoCategory", allInfoCategory);
		model.addAttribute("infoRequests", infoRequests);
		CommanUtility.userRole(request, model);
		model.addAttribute("message", "List of All Category Info details");
		return "admin/category/showCategory";
	}

	@GetMapping("/addCategory")
	private String addCategory(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Create a new Category with administrator privileges.");
		List<CategoryReq> allInfoCategory = categoryService.getAllInfoCategory();
		model.addAttribute("infoCategory", allInfoCategory);
		CommanUtility.userRole(request, model);
		return "admin/category/addCategory";
	}

	@PostMapping("/addCategory")
	private String addCategory(CategoryInfoRequest defaultRequest, MultipartFile file, Model model, HttpServletRequest request) {
		defaultRequest.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		CategoryInfoRequest addRequest = this.categoryService.addCategory(defaultRequest, file);
		CommanUtility.userRole(request, model);
		List<CategoryReq> allInfoCategory = categoryService.getAllInfoCategory();
		model.addAttribute("infoCategory", allInfoCategory);
		if (ObjectUtils.isEmpty(addRequest)) {
			model.addAttribute("message", "Category already exists!");
			return "admin/category/addCategory";
		}
		model.addAttribute("message", "Category created successfully!");
		return "admin/category/addCategory";
	}

	@GetMapping("/editCategoryInfo/{id}")
	private String editCategoryInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		CategoryInfoRequest requetInfoById = this.categoryService.getCategoryById(id);
		List<CategoryReq> allInfoCategory = categoryService.getAllInfoCategory();
		model.addAttribute("infoCategory", allInfoCategory);
		CommanUtility.userRole(request, model);
		model.addAttribute("id", id);
		model.addAttribute("command", requetInfoById);
		model.addAttribute("message", "Update Category Information");
		return "admin/category/editCategory";
	}

	@PutMapping("/editCategoryInfo/{id}")
	private String editCategoryInfos(@PathVariable String id, CategoryInfoRequest infoRequest, MultipartFile file, Model model, HttpServletRequest request) {
		CategoryInfoRequest requestById = this.categoryService.updateCategory(id, file, infoRequest);
		CommanUtility.userRole(request, model);
		List<CategoryReq> allInfoCategory = categoryService.getAllInfoCategory();
		model.addAttribute("infoCategory", allInfoCategory);
		if (ObjectUtils.isEmpty(requestById)) {
			model.addAttribute("id", id);
			model.addAttribute("command", infoRequest);
			model.addAttribute("message", "Category information could not be updated.");
			return "admin/category/editCategory";
		}
		model.addAttribute("message", "Category details have been successfully updated.");
		return "admin/category/addCategory";
	}

	@DeleteMapping("/deleteCategoryInfo/{id}")
	private String deleteCategoryInfos(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		CommanUtility.userRole(request, model);
		this.categoryService.deleteCategoryById(id);
		return "redirect:/admin/getAllCategory";
	}
	
}
