package com.saminavi.attendanceapp.employeeController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.saminavi.attendanceapp.domain.EmployeeEntity;
import com.saminavi.attendanceapp.employeeService.EmployeeService;

@Controller
public class AttendanceController {
	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/attendance")
	public String getAllEmployeeDetails(Model model) {
		  List<EmployeeEntity> employees = employeeService.getAllEmployeeDetails();
			model.addAttribute("employees", employees);
		return "attendance"; 
		
	}
}
