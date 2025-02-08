package vn.com.t3h.antino.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.t3h.antino.dao.impl.RoleDaoImpl;
import vn.com.t3h.antino.dao.impl.UserDaoImpl;
import vn.com.t3h.antino.model.request.UserRequest;
import vn.com.t3h.antino.security.Authentication;
import vn.com.t3h.antino.service.RoleService;
import vn.com.t3h.antino.service.UserService;
import vn.com.t3h.antino.service.impl.RoleServiceImpl;
import vn.com.t3h.antino.service.impl.UserServiceImpl;
import vn.com.t3h.antino.util.Constants;
import vn.com.t3h.antino.util.MapClientToSeverUtil;
import vn.com.t3h.antino.util.SessionUtil;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        req.getSession().setAttribute("message", message);
        if (req.getRequestURI().startsWith("/logout")) {
            resp.sendRedirect("/home");
            SessionUtil.removeValue(req, SessionUtil.SESSION_ID_CURRENT_USER);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRequest userRequest = MapClientToSeverUtil.toModel(UserRequest.class, req);
        String url = Authentication
                .newModel(userRequest.getUsername(), userRequest
                        .getPassword()).urlRedirect(req);
        resp.sendRedirect(url);
    }
}

