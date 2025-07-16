package com.saminavi.attendanceapp.employeeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saminavi.attendanceapp.domain.AttendaceEntity;
@Repository
public interface IAttendanceRepository  extends JpaRepository<AttendaceEntity, Long> {
	// Define methods for CRUD operations or custom queries here
	// save signin and signoff times, find attendance by employee ID, etc.
//	public AttendaceEntity saveSignIn(Long employeeId);
//	public AttendaceEntity saveSignOff(Long employeeId);
	
	// You can also use Spring Data JPA's built-in methods like save, findAll, findById, deleteById, etc.

}
