package com.t3h.controller;

import com.t3h.entity.UserEntity;
import com.t3h.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()") // dam bao user phai login moi duoc vao controller nay
public class UserController {

    @Autowired
    private IUserServices userServices;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('User')")
    public String helloUser(Model model, Principal principal){
        UserEntity userEntity = userServices.findByUserName(principal.getName());
        model.addAttribute("fullName",userEntity.getFullName());
        return "user/home";
    }
}
