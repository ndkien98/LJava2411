package vn.com.t3h.antino.controller.employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.t3h.antino.dao.impl.DepartmentDaoImpl;
import vn.com.t3h.antino.dao.impl.EmployeeDAOImpl;
import vn.com.t3h.antino.model.EmployeeModel;
import vn.com.t3h.antino.service.EmployeeService;
import vn.com.t3h.antino.service.impl.EmployeeServiceImpl;
import vn.com.t3h.antino.util.Constants;

import java.io.IOException;

@WebServlet("/action-employee")
public class ActionEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployeeService employeeService ;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeServiceImpl(new EmployeeDAOImpl(),new DepartmentDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        EmployeeModel employeeModel = employeeService.findById(id);
        req.setAttribute("model",employeeModel);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("add-employee.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numberRows = employeeService.addNewEmployee(req);
        if (numberRows > 0) {
            resp.sendRedirect("/employees?message="+ Constants.ADD_EMPLOYEE_SUCCESS);
        }

    }
}
