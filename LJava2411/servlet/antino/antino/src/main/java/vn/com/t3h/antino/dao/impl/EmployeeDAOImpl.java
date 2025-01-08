package vn.com.t3h.antino.dao.impl;


import vn.com.t3h.antino.dao.EmployeeDAO;
import vn.com.t3h.antino.model.Employee;
import vn.com.t3h.antino.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT e.employee_id, e.name, e.position, e.salary, e.hire_date, d.department_name " +
                "FROM employees e " +
                "LEFT JOIN departments d ON e.department_id = d.department_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employee.setDepartmentName(resultSet.getString("department_name"));

                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}

