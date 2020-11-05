package com.deluxe.hrapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class EmployeeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotNull(message = "Employee name can 't be empty")
	@Column(name = "name")
	private String name;
	
	@NotNull(message = "Employee address can 't be empty")
	@Column(name = "address")
	private String address;
	
	@NotNull(message = "Employee dob can 't be empty")
	@Column(name = "dob")
	private String dob;
	
	
	@NotNull(message = "Employee role can 't be empty")
	@Column(name = "role")
	private String role;

	public EmployeeModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "EmployeeModel [id=" + id + ", name=" + name + ", address=" + address + ", dob=" + dob + ", role=" + role
				+ "]";
	}

	

}