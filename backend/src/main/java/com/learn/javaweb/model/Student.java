package com.learn.javaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@XmlRootElement
@Entity
@Table(name="STUDENTS")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
	private int studentId;

    @Column(name = "ROLL_NUMBER", length = 20, nullable = false, unique = true)
    @NotNull
    @Size(max = 20)
	private String rollNumber;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
	private String firstName;

    @Column(name = "LAST_NAME", length = 100)
    @Size(max = 100)
	private String lastName;

    @Column(name = "JOINING_YEAR", nullable = false)
    @NotNull
    @Min(2000)
    @Max(3000)
    private int joiningYear;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_CODE", nullable = false)
    @NotNull
    private Department department;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
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

	public int getJoiningYear() {
		return joiningYear;
	}

	public void setJoiningYear(int joiningYear) {
		this.joiningYear = joiningYear;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", rollNumber=" + rollNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", joiningYear=" + joiningYear + ", department=" + (department != null ? department.getDepartmentCode() : null) + "]";
	}
}
