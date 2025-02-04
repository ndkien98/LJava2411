package vn.com.t3h.antino.security;


import jakarta.servlet.http.HttpServletRequest;
import vn.com.t3h.antino.dao.impl.RoleDaoImpl;
import vn.com.t3h.antino.dao.impl.UserDaoImpl;
import vn.com.t3h.antino.model.RoleModel;
import vn.com.t3h.antino.model.UserModel;
import vn.com.t3h.antino.service.RoleService;
import vn.com.t3h.antino.service.UserService;
import vn.com.t3h.antino.service.impl.RoleServiceImpl;
import vn.com.t3h.antino.service.impl.UserServiceImpl;
import vn.com.t3h.antino.util.Constants;
import vn.com.t3h.antino.util.PasswordUtils;
import vn.com.t3h.antino.util.SessionUtil;

public class AuthenticationImpl implements Authentication {
    private String userName;
    private String password;
    private UserService userService;
    private RoleService roleService;

    public AuthenticationImpl(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userService = new UserServiceImpl(new UserDaoImpl());
        this.roleService = new RoleServiceImpl(new RoleDaoImpl());
    }

    @Override
    public String urlRedirect(HttpServletRequest request) {
        UserModel userModel = userService.findUserByUserAndPassword(userName, password);
        if (userModel == null) {
            return "/login?message=loginError";
        } else {
            SessionUtil.putValue(request, "USER", userModel);
            RoleModel roleModel = roleService.getRoleById(userModel.getRoleId());
            if (Constants.ROLE.ROLE_ADMIN.name().equalsIgnoreCase(roleModel.getCode())) {
                return "/employees";
            } else if (Constants.ROLE.ROLE_USER.name().equalsIgnoreCase(roleModel.getCode())) {
                return "/home";
            }
        }
        return null;
    }

}

