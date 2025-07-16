package com.saminavi.attendanceapp.employeeController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.saminavi.attendanceapp.domain.EmployeeEntity;
import com.saminavi.attendanceapp.employeeService.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/employeeDetails")
	public String getAllEmployeeDetails(Model model) {
	    List<EmployeeEntity> employees = employeeService.getAllEmployeeDetails();
		model.addAttribute("employees", employees);
		return "employeeDetails";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(Model model) {
		  List<EmployeeEntity> employees = employeeService.getAllEmployeeDetails();
			model.addAttribute("employees", employees);
			model.addAttribute("employee", new EmployeeEntity());
		return "addEmployee"; 
		
	}
	@PostMapping("/employees/add")
	public String saveEmployeeDetails(EmployeeEntity employee) {
		
		System.out.println("Received employee details: " + employee);
	    // Save employee details
	    employeeService.saveEmployee(employee);

	    return "redirect:/addEmployee";
	}
}
