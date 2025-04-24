package com.t3h.controller;

import com.t3h.entity.UserEntity;
import com.t3h.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private IUserServices iUserServices;

    @GetMapping(value = {"/","/home"})
    public String home(Principal principal, Model model){
        if ( principal != null && principal.getName() != null){
            model.addAttribute("isSinged",true);
            UserEntity userEntity = iUserServices.findByUserName(principal.getName());
            model.addAttribute("fullName",userEntity.getFullName());
        }else {
            model.addAttribute("isSinged",false);
        }
        return "index";
    }
}
