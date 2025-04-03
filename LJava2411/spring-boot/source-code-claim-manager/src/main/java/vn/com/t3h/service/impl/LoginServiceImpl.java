package vn.com.t3h.service.impl;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.com.t3h.service.LoginService;
import vn.com.t3h.utils.Constant;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    public String processAfterLoginSuccess(){
        // lấy ra thông tin user hiện tại đang thực hiện request vừa login xong
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // nếu authentication == null hoặc chưa authen(chưa login)
        if(authentication == null || !authentication.isAuthenticated()){
            // trả ra lỗi
            throw new AuthenticationServiceException("Authentication required");
        }
        // thông tin user hiện tại
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(String.format("User %s logged in", userDetails.getUsername()));
        // lấy ra danh sách quyền của user
        List<String> roleCode = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        System.out.println(String.format("Role code: %s", roleCode));
        // kiểm tra xem có phải admin không
        boolean isAdmin = roleCode.contains(Constant.PREFIX_ROLE + Constant.ROLE_ADMIN_CODE);
        if(isAdmin){
            return "redirect:/cms/dashboard"; // role admin
        }
        return "redirect:/home"; // role user
    }
}
