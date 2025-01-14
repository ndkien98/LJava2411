package vn.com.t3h.antino.service.impl;


import vn.com.t3h.antino.dao.EmployeeDAO;
import vn.com.t3h.antino.dao.impl.EmployeeDAOImpl;
import vn.com.t3h.antino.model.EmployeeModel;
import vn.com.t3h.antino.service.EmployeeService;
import vn.com.t3h.antino.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<EmployeeModel> getAllEmployees(String name, String salary, String fromDate, String toDate, String position) {

        if (StringUtils.isBlank(name)){
            name = null;
        }
        Long salaryData = StringUtils.toLong(salary);
        if (StringUtils.isBlank(fromDate)){
            fromDate = null;
        }
        if (StringUtils.isBlank(toDate)){
            toDate = null;
        }
        if (StringUtils.isBlank(position)){
            position = null;
        }

        List<EmployeeModel> employeeModels = employeeDAO.getAllEmployees2(name,salaryData,fromDate,toDate,position);
        return employeeModels;

    }
}