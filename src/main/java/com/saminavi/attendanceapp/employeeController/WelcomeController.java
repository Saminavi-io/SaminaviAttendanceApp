package com.saminavi.attendanceapp.employeeController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@GetMapping("/welcome")
	public String getAllEmployeeDetails(Model model) {
		return "welcome"; 
		// Logic to get employee details
	}
}
