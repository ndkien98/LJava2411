package com.t3h.controller;

import com.t3h.entity.UserEntity;
import com.t3h.model.dto.UserDto;
import com.t3h.service.IUserServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

    private final IUserServices iUserServices;

    public AdminController(IUserServices iUserServices) {
        this.iUserServices = iUserServices;
    }

    @GetMapping()
//    @PreAuthorize("hasAuthority('Admin')")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public String getUsers(Model model, Principal principal){
        List<UserDto> userDtos = iUserServices.getUsers();
        model.addAttribute("listUsers",userDtos);
        UserEntity userEntity = iUserServices.findByUserName(principal.getName());
        model.addAttribute("fullName",userEntity.getFullName());
        return "admin/users";
    }
}
