package com.learn.javaweb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DepartmentDto {
	private int departmentId;
	
    @NotNull(message = "Department code is required")
    @Size(max = 10, message = "Department code must be at most 10 characters")
	private String departmentCode;

    @NotNull(message = "Department name is required")
    @Size(max = 100, message = "Department name must be at most 100 characters")
	private String departmentName;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
