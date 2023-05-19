package io.iamofoe.departmentservice.controller;

import io.iamofoe.departmentservice.client.EmployeeClient;
import io.iamofoe.departmentservice.model.Department;
import io.iamofoe.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentRepository repository;
    private final EmployeeClient employeeClient;

    @Autowired
    public DepartmentController(DepartmentRepository repository, EmployeeClient employeeClient) {
        this.repository = repository;
        this.employeeClient = employeeClient;
    }

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Add department: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll(){
        LOGGER.info("Find all departments");
        return repository.findAllDepartments();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable long id) {
        LOGGER.info("Find department by id: {}", id);
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Find all departments with employees");
        List<Department> departments = repository.findAllDepartments();
        departments
                .forEach(department -> department.setEmployees(employeeClient.findByDepartment(department.getId())));
        return departments;
    }


}
