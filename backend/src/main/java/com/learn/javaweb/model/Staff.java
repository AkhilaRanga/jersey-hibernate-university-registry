package com.learn.javaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@XmlRootElement
@Entity
@Table(name="STAFF")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STAFF_ID")
	private int staffId;

    @Column(name = "STAFF_CODE", length = 20, nullable = false, unique = true)
    @NotNull
    @Size(max = 20)
	private String staffCode;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
	private String firstName;

    @Column(name = "LAST_NAME", length = 100)
    @Size(max = 100)
	private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 50)
    @Size(max = 50)
    private StaffRole role;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_CODE", nullable = false)
    @NotNull
    private Department department;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
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

	public StaffRole getRole() {
		return role;
	}

	public void setRole(StaffRole role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffCode=" + staffCode + ", firstName=" + firstName + ", lastName="
				+ lastName + ", role=" + role + ", department=" + (department != null ? department.getDepartmentCode() : null) + "]";
	}
}
