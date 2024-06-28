package YahiaLakrikba.ES_28._6.services;


import YahiaLakrikba.ES_28._6.entities.Employee;
import YahiaLakrikba.ES_28._6.exeptions.NotFoundException;
import YahiaLakrikba.ES_28._6.repositories.EmployeeRepository;
import YahiaLakrikba.ES_28._6.exeptions.EmployeePatchRequest;
import YahiaLakrikba.ES_28._6.exeptions.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        return employeeRepository.save(updateEmployeeFromRequest(request, employee));
    }

    public Employee updateEmployee(int id, EmployeeRequest request) throws NotFoundException {
        Employee employee = findEmployeeById(id);
        return employeeRepository.save(updateEmployeeFromRequest(request, employee));
    }

    public Employee patchUpdateEmployee(int id, EmployeePatchRequest request) throws NotFoundException {
        Employee employee = findEmployeeById(id);
        if (request.getFirstName() != null) {
            employee.setName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            employee.setSureName(request.getLastName());
        }
        if (request.getUsername() != null) {
            employee.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            employee.setEmail(request.getEmail());
        }
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int id) throws NotFoundException {
        return findEmployeeById(id);
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee setProfilePicture(int id, String profilePicture) throws NotFoundException {
        Employee employee = findEmployeeById(id);
        employee.setProfilePicture(profilePicture);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) throws NotFoundException {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
    }

    private Employee updateEmployeeFromRequest(EmployeeRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setSureName(request.getSureName());
        employee.setUsername(request.getUsername());
        employee.setEmail(request.getEmail());
        return employee;
    }

    private Employee findEmployeeById(int id) throws NotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new NotFoundException("Employee with id=" + id + " not found");
        }
        return employeeOptional.get();
    }
}
