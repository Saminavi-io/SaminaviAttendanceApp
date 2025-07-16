package com.saminavi.attendanceapp.employeeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saminavi.attendanceapp.domain.EmployeeEntity;
@Repository
public interface IEmployeeRepository  extends JpaRepository<EmployeeEntity, Long> {
	// Define methods for CRUD operations or custom queries here
//	public void saveEmployee(EmployeeEntity employee);
//	public EmployeeEntity findEmployeeById(Long id);
	
	// You can also use Spring Data JPA's built-in methods like save, findAll, findById, deleteById, etc.

}
