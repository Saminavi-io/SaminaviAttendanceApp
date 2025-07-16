package com.saminavi.attendanceapp.domain;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class AttendaceEntity {
	
	@Id
	@Column(name = "attendance_id")
	private Long attendanceId;
	@Column(name = "employee_id")
	private Long employeeId;
	@Column(name = "signin_time")
	private Time signinTime;
	@Column(name = "signoff_time")
	private Date signoffTime;
	@Column(name = "attendance_date")
	public Date attendacneDate;
	public Long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Time getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(Time signinTime) {
		this.signinTime = signinTime;
	}
	public Date getSignoffTime() {
		return signoffTime;
	}
	public void setSignoffTime(Date signoffTime) {
		this.signoffTime = signoffTime;
	}
	public Date getAttendacneDate() {
		return attendacneDate;
	}
	public void setAttendacneDate(Date attendacneDate) {
		this.attendacneDate = attendacneDate;
	}

	
}
