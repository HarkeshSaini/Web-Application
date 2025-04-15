package com.spring.security.controller;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.security.interfaces.UserInfoService;
import com.spring.security.object.UserInfoRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final UserInfoService registerService;
	
    public AdminController(UserInfoService registerService) {
		this.registerService = registerService;
	}

	@GetMapping("/dashboard")
    private String dashboard(Model model){
        List<UserInfoRequest> allserRegistration = this.registerService.getAllUser();
        model.addAttribute("registrationsDetail",allserRegistration);
        return "admin/dashboard";
    }
	
	@GetMapping("/getAllUser")
    private String getAllUser(Model model){
        List<UserInfoRequest> allserRegistration = this.registerService.getAllUser();
        model.addAttribute("registrationsDetail",allserRegistration);
        return "admin/showUser";
    }

    @GetMapping("/addUser")
    private String addUser(Model model){
        model.addAttribute("success","Add new user for admin!...");
        return "admin/addUser";
    }

    @PostMapping("/addUser")
    private String addUser(@NotNull UserInfoRequest userRequest,Model model){
        UserInfoRequest addUser = this.registerService.addUser(userRequest);
        if(ObjectUtils.isEmpty(addUser)) {
        	model.addAttribute("success","User not update");
        }
        model.addAttribute("success","User info successfully submit");
        return "admin/addUser";
    }
    
    @GetMapping("/editUserInfo/{id}")
    private String eitUserRegistrationInfoGet(@NotNull @PathVariable Integer id,Model model){
    	UserInfoRequest userById = this.registerService.getUserById(id);
        model.addAttribute("id" ,id);
        model.addAttribute("command" ,userById);
        model.addAttribute("success","Edit user Info..");
        return "admin/editUser";
    }

    @PostMapping("/editUserInfo/{id}")
    private String eitUserRegistrationInfoPost(@NotNull @PathVariable int id,@NotNull UserInfoRequest infoRequest,Model model){
        UserInfoRequest userById = this.registerService.getUserById(id);
        model.addAttribute("id" ,id);
        model.addAttribute("command" ,userById);
        model.addAttribute("success","Successfully updated user Info...");
        return "admin/addUser";
    }

    @GetMapping("/deleteUserInfo")
    private String deleteUserInfo(@NotNull @PathVariable Integer id,Model model){
        this.registerService.deleteUserById(id);
        return "redirect:/admin/getAllUser";
    }
}
