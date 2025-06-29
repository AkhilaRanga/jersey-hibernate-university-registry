package com.learn.javaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@XmlRootElement
@Entity
@Table(name="DEPARTMENTS")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
	private int departmentId;

    @Column(name = "DEPARTMENT_CODE", length = 10, nullable = false, unique = true)
    @NotNull
    @Size(max = 10)
	private String departmentCode;

    @Column(name = "DEPARTMENT_NAME", length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
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

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentCode=" + departmentCode + ", departmentName="
				+ departmentName + "]";
	}
}
