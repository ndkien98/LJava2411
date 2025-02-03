package vn.com.t3h.antino.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.com.t3h.antino.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getAllEmployees(String name,String salary,String fromDate,String toDate,String position);

    int addNewEmployee(HttpServletRequest request);

    EmployeeModel findById(String id);
}
