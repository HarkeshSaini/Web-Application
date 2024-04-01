package com.spring.security.controller;

import com.spring.security.object.UserRegistrationObject;
import com.spring.security.service.UserRegistrationServices;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private UserRegistrationServices userRegistrationServices;

    @GetMapping("/dashboard")
    private String dashboardAdmin(Model model){
        var userDetail = userRegistrationServices.getAllserRegistration();
        model.addAttribute("registrationsDetail",userDetail);
        return "dashboard";
    }

    @GetMapping("/addUser")
    private String registrationUserDetailAdd(Model model){
        model.addAttribute("success","Add new user for admin!...");
        return "addUser";
    }

    @PostMapping("/addUser")
    private String registrationUserDetailSubmit(@NotNull UserRegistrationObject userRegistrationObject,Model model){
        final var userRegistration = userRegistrationServices.submitNewUserRegistration(userRegistrationObject);
        model.addAttribute("success","User info successfully submit!..");
        return "addUser";
    }
    
    @GetMapping("/editUserInfo/{id}")
    private String eitUserRegistrationInfoGet(@NotNull @PathVariable Integer id,Model model){
        final var byIdRegistrationUser = userRegistrationServices.findByIdRegistrationUser(id);
        model.addAttribute("id" ,id);
        model.addAttribute("command" ,byIdRegistrationUser);
        model.addAttribute("success","Edit user Info..");
        return "editUser";
    }

    @PostMapping("/editUserInfo/{id}")
    private String eitUserRegistrationInfoPost(@NotNull @PathVariable Integer id,@NotNull UserRegistrationObject userRegistrationObject,Model model){
        final var byIdRegistrationUser = userRegistrationServices.findByIdRegistrationUser(id);
        model.addAttribute("id" ,id);
        model.addAttribute("command" ,byIdRegistrationUser);
        model.addAttribute("success","Successfully updated user Info...");
        return "addUser";
    }

    @GetMapping("/deleteUserInfo")
    private String deleteUserInfo(@NotNull @PathVariable Integer id,Model model){
        final var stringResponseEntity = userRegistrationServices.deleteUserRegistrationInfo(id);
        System.out.println(stringResponseEntity);
        return "redirect:/dashboard";
    }

    @GetMapping("/deleteUserInfoUndo")
    private String deleteUndoUserInfo(@NotNull @PathVariable Integer id,Model model){
        final var stringResponseEntity = userRegistrationServices.undoUserRegistrationInfoByUserId(id);
        System.out.println(stringResponseEntity);
        return "redirect:/dashboard";
    }

}
