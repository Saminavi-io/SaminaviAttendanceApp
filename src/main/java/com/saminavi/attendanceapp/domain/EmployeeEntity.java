package com.saminavi.attendanceapp.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "employee")
@ToString
@SequenceGenerator(name = "employee_seq", sequenceName="employee_seq", allocationSize = 1)
public class EmployeeEntity {
	
	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "employee_seq")
	private Long employeeId;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "dob")
	private LocalDate dob;

	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
}
