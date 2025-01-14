package vn.com.t3h.antino.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.t3h.antino.dao.impl.EmployeeDAOImpl;
import vn.com.t3h.antino.model.EmployeeModel;
import vn.com.t3h.antino.service.EmployeeService;
import vn.com.t3h.antino.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    private final EmployeeService employeeService;

    public EmployeeServlet() {
        employeeService = new EmployeeServiceImpl(new EmployeeDAOImpl());
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Employee> employees = employeeService.getAllEmployees();
//        request.setAttribute("employees", employees);
//        request.getRequestDispatcher("employees.jsp").forward(request, response);
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String salary = request.getParameter("salary");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String position = request.getParameter("position");
        List<EmployeeModel> employeeModels = employeeService.getAllEmployees(name,salary,fromDate,toDate,position);
        // Gửi dữ liệu nhân viên vào JSP
        request.setAttribute("employeeModels", employeeModels);
        request.getRequestDispatcher("employees.jsp").forward(request, response);
    }
}