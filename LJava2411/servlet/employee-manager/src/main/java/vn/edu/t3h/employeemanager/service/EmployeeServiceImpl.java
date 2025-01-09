package vn.edu.t3h.employeemanager.service;

import vn.edu.t3h.employeemanager.dao.EmployeeDao;
import vn.edu.t3h.employeemanager.model.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployee() {
//        EmployeeDao employeeDao = new EmployeeDaoImpl();
        return employeeDao.getAllEmployee();
    }
}
