package vn.com.t3h.antino.service;

import vn.com.t3h.antino.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getAllEmployees(String name,String salary,String fromDate,String toDate,String position);
}
