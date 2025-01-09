package vn.edu.t3h.employeemanager.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager.dao.EmployeeDao;
import vn.edu.t3h.employeemanager.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.employeemanager.model.Employee;
import vn.edu.t3h.employeemanager.service.EmployeeService;
import vn.edu.t3h.employeemanager.service.EmployeeServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EmployeeServlet",value = "/employee")
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
        List<Employee> employees = employeeService.getAllEmployee();
        req.setAttribute("employeeData",employees);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee-list.jsp");
        requestDispatcher.forward(req,resp);
    }
}
