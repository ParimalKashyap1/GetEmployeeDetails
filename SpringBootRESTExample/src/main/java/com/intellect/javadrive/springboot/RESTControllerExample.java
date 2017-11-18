package com.intellect.javadrive.springboot;

import java.util.List;

import javax.validation.Valid;

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

import com.intellect.javadrive.springboot.Employee;
import com.intellect.javadrive.springboot.EmployeeRepository;

/**
 * Created by Parimal Kashyap.
 */
@RestController
@RequestMapping("/api")
public class RESTControllerExample {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/employee")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long EmployeeId) {
		Employee Employee = employeeRepository.findOne(EmployeeId);
		if (Employee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(Employee);
	}

	@PostMapping("/employee")
	public Employee createEmployee(@Valid @RequestBody Employee Employee) {
		return employeeRepository.save(Employee);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateNote(@PathVariable(value = "id") Long EmployeeId,
			@Valid @RequestBody Employee EmployeeDetails) {
		Employee Employee = employeeRepository.findOne(EmployeeId);
		if (Employee == null) {
			return ResponseEntity.notFound().build();
		}
		Employee.setActive(EmployeeDetails.isActive());
		Employee.setBirthDate(EmployeeDetails.getBirthDate());
		Employee.setEmail(EmployeeDetails.getEmail());
		Employee.setfName(EmployeeDetails.getfName());
		Employee.setlName(EmployeeDetails.getlName());
		Employee.setPinCode(EmployeeDetails.getPinCode());
		Employee updatedEmployee = employeeRepository.save(Employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> deleteNote(@PathVariable(value = "id") Long EmployeeId) {
		Employee Employee = employeeRepository.findOne(EmployeeId);
		if (Employee == null) {
			return ResponseEntity.notFound().build();
		}

		employeeRepository.delete(Employee);
		return ResponseEntity.ok().build();
	}
}
