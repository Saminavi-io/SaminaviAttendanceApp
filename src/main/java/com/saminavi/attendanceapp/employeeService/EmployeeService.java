package com.saminavi.attendanceapp.employeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saminavi.attendanceapp.domain.EmployeeEntity;
import com.saminavi.attendanceapp.employeeRepository.IEmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private  IEmployeeRepository employeeRepository;
	
	// Logic to handle employee data, such as fetching from a database or processing data
	public List<EmployeeEntity> getAllEmployeeDetails() {
		// Placeholder for actual logic
		return employeeRepository.findAll();
	}
	
	public void saveEmployee(EmployeeEntity employee) {
		// Placeholder for actual logic to add an employee
		employeeRepository.save(employee);
	}
//	
//	public String deleteEmployee(String employeeId) {
//		// Placeholder for actual logic to delete an employee
//		return "Employee with ID " + employeeId + " deleted successfully";
//	}
}
