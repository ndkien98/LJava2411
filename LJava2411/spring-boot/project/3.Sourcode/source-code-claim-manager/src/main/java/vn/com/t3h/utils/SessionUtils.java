package vn.com.t3h.utils;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SessionUtils {

    public static UserDetails getUserPrinciple() {
        // lấy ra thông tin user hiện tại đang thực hiện request vừa login xong
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // nếu authentication == null hoặc chưa authen(chưa login)
        if(authentication == null || !authentication.isAuthenticated()){
            // trả ra lỗi
            throw new AuthenticationServiceException("Authentication required");
        }
        // thông tin user hiện tại
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails;
    }
}
