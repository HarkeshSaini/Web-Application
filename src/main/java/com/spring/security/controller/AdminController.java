package com.spring.security.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.exception.NotFoundException;
import com.spring.security.interfaces.BlogInfoService;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.interfaces.ContactInfoService;
import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.interfaces.ReviewsInfoService;
import com.spring.security.interfaces.UserInfoService;
import com.spring.security.request.BlogInfoRequest;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;
import com.spring.security.request.ContactInfoRequest;
import com.spring.security.request.DefaultInfoRequest;
import com.spring.security.request.ReviewInfoRequest;
import com.spring.security.request.SubscribeInfoRequest;
import com.spring.security.request.UserInfoRequest;
import com.spring.security.utility.CommonUtility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	private final BlogInfoService blogService;
	private final UserInfoService registerService;
	private final CategoryInfoService categoryService;
	private final ReviewsInfoService reviewsInfoService;
	private final DefaultInfoService defaultInfoService;
	private final ContactInfoService contactInfoService;

	public AdminController(UserInfoService registerService, ContactInfoService contactInfoService,BlogInfoService blogService, DefaultInfoService defaultInfoService, CategoryInfoService categoryService, ReviewsInfoService reviewsInfoService) {
		this.blogService = blogService;
		this.registerService = registerService;
		this.categoryService = categoryService;
		this.reviewsInfoService = reviewsInfoService;
		this.defaultInfoService = defaultInfoService;
		this.contactInfoService = contactInfoService;
	}

	// ===================== LOGIN AND DASHBOARD =====================

	@GetMapping
	public String dashBoardLogin(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Welcome to the admin login! Please sign in");
		CommonUtility.userRole(request, model);
		return "admin/login";
	}

	@GetMapping("/dashboard")
	private String dashboard(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Admin Panel – Welcome");
		CommonUtility.userRole(request, model);
		return "admin/dashboard";
	}

	// ===================== USER MANAGEMENT =====================

	@GetMapping("/getAllUser")
	private String getAllUser(HttpServletRequest request, Model model) {
		try {
			List<UserInfoRequest> userInfo = this.registerService.getAllUser();
			model.addAttribute("userInfo", userInfo);
			CommonUtility.userRole(request, model);
			model.addAttribute("message", "List of All Registered Users");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/user/showUser";
	}

	@GetMapping("/addUser")
	private String addUser(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Create a new user with administrator privileges.");
		CommonUtility.userRole(request, model);
		return "admin/user/addUser";
	}

	@PostMapping("/addUser")
	private String addUser(UserInfoRequest userRequest, MultipartFile file, Model model, HttpServletRequest request) {
		try {
			userRequest.setCreateDate(new Timestamp(System.currentTimeMillis()));
			UserInfoRequest addUser = this.registerService.addUser(userRequest, file);
			CommonUtility.userRole(request, model);
			if (ObjectUtils.isEmpty(addUser)) {
				model.addAttribute("message", "User already exists!");
				return "admin/user/addUser";
			}
			model.addAttribute("message", "User created successfully!");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/user/addUser";
	}

	@GetMapping("/editUserInfo/{id}")
	private String editUserInfo(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			UserInfoRequest userById = this.registerService.getUserById(id);
			CommonUtility.userRole(request, model);
			model.addAttribute("id", id);
			model.addAttribute("command", userById);
			model.addAttribute("message", "Update User Information");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/user/editUser";
	}

	@PostMapping("/editUserInfo/{id}")
	private String editUserInfo(@PathVariable String id, UserInfoRequest infoRequest, MultipartFile file, Model model,
			HttpServletRequest request) {
		try {
			UserInfoRequest userById = this.registerService.updateUser(id, file, infoRequest);
			CommonUtility.userRole(request, model);
			if (ObjectUtils.isEmpty(userById)) {
				model.addAttribute("id", id);
				model.addAttribute("command", infoRequest);
				model.addAttribute("message", "User information could not be updated.");
				return "admin/user/editUser";
			}
			model.addAttribute("message", "User details have been successfully updated.");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/user/editUser";
	}

	@GetMapping("/deleteUserInfo/{id}")
	private String deleteUserInfo(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			this.registerService.deleteUserById(id);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "redirect:/admin/getAllUser";
	}

	// ===================== CONTACT MANAGEMENT =====================

	@GetMapping("/showAllContactInfo")
	private String showAllContactInfo(HttpServletRequest request, Model model) {
		try {
			List<ContactInfoRequest> contactInfo = this.contactInfoService.showAllContactInfo();
			model.addAttribute("contactInfo", contactInfo);
			CommonUtility.userRole(request, model);
			model.addAttribute("message", "List of Users Who Submitted the Contact Us Form");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/contactUs";
	}

	@GetMapping("/deleteContactUsInfo/{id}")
	private String deleteContactUsInfo(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			this.contactInfoService.deleteById(id);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "redirect:/admin/showAllContactInfo";
	}
	
	@GetMapping("/showAllSubscribeInfo")
	private String showAllSubscribeInfo(HttpServletRequest request, Model model) {
		try {
			List<SubscribeInfoRequest> subscribeInfo = this.contactInfoService.showAllSubscribeInfo();
			model.addAttribute("subscribeInfos", subscribeInfo);
			CommonUtility.userRole(request, model);
			model.addAttribute("message", "List of Users Who Submitted the Subscribe Us Form");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/subscribeUs";
	}

	@GetMapping("/deleteSubscribeInfo/{id}")
	private String deleteSubscribeInfo(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			this.contactInfoService.deleteSubscribeInfoById(id);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "redirect:/admin/showAllSubscribeInfo";
	}

	// ===================== BLOG MANAGEMENT =====================

	@GetMapping("/getAllBlog")
	private String getAllBlog(HttpServletRequest request, Model model) {
		try {
			List<BlogInfoRequest> blogRequest = this.blogService.getAllBlog();
			model.addAttribute("blogRequest", blogRequest);
			CommonUtility.userRole(request, model);
			model.addAttribute("message", "List of All Blog Details");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/blog/showBlog";
	}

	@GetMapping("/addBlog")
	private String addBlog(HttpServletRequest request, Model model) {
		model.addAttribute("message", "Create a new Blog with administrator privileges.");
		CommonUtility.userRole(request, model);
		return "admin/blog/addBlog";
	}

	@PostMapping("/addBlog")
	private String addBlogs(BlogInfoRequest blogRequest, MultipartFile file, Model model, HttpServletRequest request) {
		try {
			blogRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			BlogInfoRequest addBlog = this.blogService.addBlog(blogRequest, file);
			CommonUtility.userRole(request, model);
			if (ObjectUtils.isEmpty(addBlog)) {
				model.addAttribute("message", "Blog already exists!");
				return "admin/blog/addBlog";
			}
			model.addAttribute("message", "Blog created successfully!");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/blog/addBlog";
	}

	@GetMapping("/editBlogInfo/{id}")
	private String editBlogInfo(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			BlogInfoRequest blogById = this.blogService.getBlogById(id);
			CommonUtility.userRole(request, model);
			model.addAttribute("id", id);
			model.addAttribute("command", blogById);
			model.addAttribute("message", "Update Blog Information");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/blog/editBlog";
	}

	@PostMapping("/editBlogInfo/{id}")
	private String editBlogInfos(@PathVariable String id, BlogInfoRequest infoRequest, MultipartFile file, Model model,
			HttpServletRequest request) {
		try {
			BlogInfoRequest blogById = this.blogService.updateBlog(id, file, infoRequest);
			CommonUtility.userRole(request, model);
			if (ObjectUtils.isEmpty(blogById)) {
				model.addAttribute("id", id);
				model.addAttribute("command", infoRequest);
				model.addAttribute("message", "Blog information could not be updated.");
				return "admin/blog/editBlog";
			}
			model.addAttribute("message", "Blog details have been successfully updated.");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/blog/addBlog";
	}

	@GetMapping("/deleteBlogInfo/{id}")
	private String deleteBlogInfos(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			this.blogService.deleteBlogById(id);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "redirect:/admin/getAllBlog";
	}

	// ===================== CATEGORY MANAGEMENT =====================

	@GetMapping("/getAllCategory")
	private String getAllCategory(HttpServletRequest request, Model model) {
		try {
			List<CategoryReq> infoRequests = this.categoryService.getAllInfoCategory();
			model.addAttribute("infoCategory", infoRequests);
			List<CategoryInfoRequest> categoryInfo = this.categoryService.getAllCategoryContant();
			model.addAttribute("categoryInfo", categoryInfo);
			CommonUtility.userRole(request, model);
			model.addAttribute("message", "All Categories");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/category/showCategory";
	}

	@GetMapping("/addCategory")
	private String addCategory(HttpServletRequest request, Model model) {
		List<CategoryReq> infoRequests = this.categoryService.getAllInfoCategory();
		model.addAttribute("infoCategory", infoRequests);
		model.addAttribute("message", "Create a new Category");
		CommonUtility.userRole(request, model);
		return "admin/category/addCategory";
	}

	@PostMapping("/addCategory")
	private String addCategory(CategoryInfoRequest categoryRequest, MultipartFile file, Model model,
			HttpServletRequest request) {
		try {
			categoryRequest.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			ResponseEntity<CategoryInfoRequest> addCategory = this.categoryService.addCategory(categoryRequest, file);
			CommonUtility.userRole(request, model);
			if (ObjectUtils.isEmpty(addCategory.getBody())) {
				model.addAttribute("message", "Category already exists!");
				return "admin/category/addCategory";
			}
			model.addAttribute("message", "Category created successfully!");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/category/addCategory";
	}

	@GetMapping("/editCategoryInfo/{id}")
	private String editCategoryInfo(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			ResponseEntity<CategoryInfoRequest> categoryById = this.categoryService.getCategoryById(id);
			CommonUtility.userRole(request, model);
			List<CategoryReq> infoRequests = this.categoryService.getAllInfoCategory();
			model.addAttribute("infoCategory", infoRequests);
			model.addAttribute("id", id);
			model.addAttribute("command", categoryById.getBody());
			model.addAttribute("message", "Update Category Information");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/category/editCategory";
	}

	@PostMapping("/editCategoryInfo/{id}")
	private String editCategoryInfos(@PathVariable String id, CategoryInfoRequest infoRequest, MultipartFile file,
			Model model, HttpServletRequest request) {
		try {
			ResponseEntity<CategoryInfoRequest> categoryById = this.categoryService.updateCategory(id, file,
					infoRequest);
			CommonUtility.userRole(request, model);
			if (ObjectUtils.isEmpty(categoryById.getBody())) {
				model.addAttribute("id", id);
				model.addAttribute("command", infoRequest);
				model.addAttribute("message", "Category information could not be updated.");
				return "admin/category/editCategory";
			}
			model.addAttribute("message", "Category details have been successfully updated.");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/category/addCategory";
	}

	@GetMapping("/deleteCategoryInfo/{id}")
	private String deleteCategoryInfos(@PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			this.categoryService.deleteCategoryById(id);
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "redirect:/admin/getAllCategory";
	}

	// ===================== Default MANAGEMENT =====================

	@GetMapping("/getAllDefaultContant")
	public String getAllDefaultContant(HttpServletRequest request, Model model) {
		try {
			List<DefaultInfoRequest> defaultInfoRequests = this.defaultInfoService.getAllDefaultContant();
			model.addAttribute("defaultInfoRequests", defaultInfoRequests);
			CommonUtility.userRole(request, model);
			model.addAttribute("message", "List of All Default Info details");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/default/showDefault";
	}

	@GetMapping("/addDefault")
	public String addDefault(HttpServletRequest request, Model model) {
		CommonUtility.userRole(request, model);
		model.addAttribute("message", "Create a new Default with administrator privileges.");
		return "admin/default/addDefault";
	}

	@PostMapping("/addDefault")
	public String addDefault(DefaultInfoRequest defaultRequest, MultipartFile file, Model model,
			HttpServletRequest request) {
		try {
			defaultRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			ResponseEntity<DefaultInfoRequest> addedDefault = this.defaultInfoService.addDefault(defaultRequest, file);
			CommonUtility.userRole(request, model);

			if (ObjectUtils.isEmpty(addedDefault.getBody())) {
				model.addAttribute("message", "Default already exists!");
				return "admin/default/addDefault";
			}

			model.addAttribute("message", "Default created successfully!");
			return "redirect:/admin/getAllDefaultContant"; // Post/Redirect/Get pattern
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
	}

	@GetMapping("/editDefaultInfo/{id}")
	public String editDefaultInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		try {
			ResponseEntity<DefaultInfoRequest> defaultById = this.defaultInfoService.getDefaultById(id);
			CommonUtility.userRole(request, model);
			model.addAttribute("id", id);
			model.addAttribute("command", defaultById.getBody());
			model.addAttribute("message", "Update Default Information");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/default/editDefault";
	}

	@PostMapping("/editDefaultInfo/{id}")
	public String editDefaultInfos(@PathVariable String id, DefaultInfoRequest infoRequest, MultipartFile file,
			Model model, HttpServletRequest request) {
		try {
			ResponseEntity<DefaultInfoRequest> updatedDefault = this.defaultInfoService.updateDefault(id, file,
					infoRequest);
			CommonUtility.userRole(request, model);

			if (ObjectUtils.isEmpty(updatedDefault.getBody())) {
				model.addAttribute("id", id);
				model.addAttribute("command", infoRequest);
				model.addAttribute("message", "Default information could not be updated.");
				return "admin/default/editDefault";
			}

			model.addAttribute("message", "Default details have been successfully updated.");
			return "redirect:/admin/getAllDefaultContant"; // Redirect after Post to avoid resubmission
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
	}

	@GetMapping("/deleteDefaultInfo/{id}")
	public String deleteDefaultInfos(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			this.defaultInfoService.deleteDefaultById(id);
			return "redirect:/admin/getAllDefaultContant"; // Redirect to avoid resubmission
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
	}
	
	//===============================REVIEWS-MANAGEMENT================================
	
	@GetMapping("/getAllReviews")
	public String reviewInfoRequests(HttpServletRequest request,Model model){
		List<ReviewInfoRequest> infoRequests=reviewsInfoService.reviewsInfoService();
		try {
			model.addAttribute("infoRequests", infoRequests);
			CommonUtility.userRole(request, model);
			model.addAttribute("message", "List of All Reviews Info details");
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
		return "admin/review";
	}
	
	@GetMapping("/deleteReviewInfo/{id}")
	public String deleteReviewInfo(@NotNull @PathVariable String id, Model model, HttpServletRequest request) {
		try {
			CommonUtility.userRole(request, model);
			this.reviewsInfoService.deleteReviewInfo(id);
			return "redirect:/admin/getAllReviews"; // Redirect to avoid resubmission
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
	}
}
