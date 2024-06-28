package YahiaLakrikba.ES_28._6.controller;


import YahiaLakrikba.ES_28._6.entities.Employee;
import YahiaLakrikba.ES_28._6.exeptions.BadRequestException;
import YahiaLakrikba.ES_28._6.exeptions.NotFoundException;
import YahiaLakrikba.ES_28._6.requests.EmployeePatchRequest;
import YahiaLakrikba.ES_28._6.requests.EmployeeRequest;
import YahiaLakrikba.ES_28._6.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) throws NotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @Validated EmployeeRequest employeeRequest,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getAllErrors());
        }
        Employee createdEmployee = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id,
                                            @RequestBody @Validated EmployeeRequest employeeRequest,
                                            BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getAllErrors());
        }
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdateEmployee(@PathVariable int id,
                                                 @RequestBody @Validated EmployeePatchRequest employeePatchRequest,
                                                 BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getAllErrors());
        }
        Employee patchedEmployee = employeeService.patchUpdateEmployee(id, employeePatchRequest);
        return ResponseEntity.ok(patchedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) throws NotFoundException {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee with id=" + id + " deleted successfully");
    }
}
