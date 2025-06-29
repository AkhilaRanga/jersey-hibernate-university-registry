package com.learn.javaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@XmlRootElement
@Entity
@Table(name="ADMINS")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID")
	private int adminId;

    @Column(name = "PASSWORD", length = 60, nullable = false)
    @NotNull
    @Size(max = 60)
	private String password;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAFF_CODE", nullable = false)
    @NotNull
    private Staff staff;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", staff=" + (staff != null ? staff.getStaffCode() : null) + "]";
	}
}
