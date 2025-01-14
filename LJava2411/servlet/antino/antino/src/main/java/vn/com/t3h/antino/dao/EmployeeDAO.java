package vn.com.t3h.antino.dao;

import vn.com.t3h.antino.model.EmployeeModel;

import java.util.List;

public interface EmployeeDAO {

    List<EmployeeModel> getAllEmployees();

    List<EmployeeModel> getAllEmployees2(String name, Long salary, String fromDate, String toDate, String position);
}
