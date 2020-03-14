package com.pramod.first.controller;

import com.pramod.first.Exception.ResourceNotFoundException;
import com.pramod.first.model.Employee;
import com.pramod.first.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
        Employee employee = repository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: " + employeeId));
        return ResponseEntity.ok().body(employee);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> EmployeeById(@PathVariable(value = "id") long employeeId, @RequestBody Employee updatedEmployee) throws ResourceNotFoundException {
        Employee employee = repository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: " + employeeId));
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        repository.save(employee);
        return ResponseEntity.ok().body(employee);

    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
        Employee employee = repository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: " + employeeId));
        repository.delete(employee);
    }
}
