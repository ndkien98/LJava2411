package vn.com.t3h.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
@Controller: trả về dữ liệu dạng html, file view
 */
@Controller
public class HomeController {
    /*
    GetMapping: sử dụng phương thức http get
    PostMapping: sử dụng phương thức http post
     */
    @GetMapping("/home")
    public String getHome(){
        // trả về tên file trang-chu.jsp
        return "trang-chu";
    }
}
