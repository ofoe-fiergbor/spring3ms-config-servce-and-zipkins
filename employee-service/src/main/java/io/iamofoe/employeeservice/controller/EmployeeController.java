package io.iamofoe.employeeservice.controller;

import io.iamofoe.employeeservice.model.Employee;
import io.iamofoe.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return repository.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> findAll(){
        LOGGER.info("All Employees");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id) {
        LOGGER.info("Find Employee by id: {}", id);
        return repository.findById(id);
    }

    @GetMapping("/department/{id}")
    public List<Employee> findByDepartment(@PathVariable long id) {
        LOGGER.info("Find by department id: {}", id);
        return repository.findByDepartmentId(id);
    }

}
