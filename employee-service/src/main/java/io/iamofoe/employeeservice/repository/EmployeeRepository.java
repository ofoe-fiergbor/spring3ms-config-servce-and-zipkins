package io.iamofoe.employeeservice.repository;

import io.iamofoe.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private static final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee findById(long id) {
        return employees.stream().filter(employee -> employee.id() == id).findFirst().orElseThrow();
    }

    public List<Employee> findAll() {
        return employees;
    }

    public List<Employee> findByDepartmentId(long id) {
        return employees.stream().filter(employee -> employee.departmentId() == id).toList();
    }
}
