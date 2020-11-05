package com.deluxe.hrapp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.deluxe.hrapp.model.EmployeeModel;
import com.deluxe.hrapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl {

	@Autowired
	private EmployeeRepository employeeRepo;

	public EmployeeModel save(EmployeeModel employee) {
		return employeeRepo.save(employee);
	}

	public EmployeeModel findByemployeeId(Integer employeeId) {
		return employeeRepo.findById(employeeId);
	}

	public List<EmployeeModel> findAllEmployeeList() {
		return employeeRepo.findAll();
	}

	public void deleteByemployeeId(Integer employeeId) {
		EmployeeModel employee = employeeRepo.findById(employeeId);
		employeeRepo.delete(employee);
	}
	
	public EmployeeModel updateEmployeeById(Integer employeeId, EmployeeModel emp) {
		EmployeeModel employee = employeeRepo.findById(employeeId);
		
		if(!StringUtils.isEmpty(emp.getAddress())) {
			employee.setAddress(emp.getAddress());
		}
		
		if(!StringUtils.isEmpty(emp.getDob())) {
			employee.setDob(emp.getDob());
		}
		
		if(!StringUtils.isEmpty(emp.getName())) {
			employee.setName(emp.getName());
		}
		if(!StringUtils.isEmpty(emp.getRole())) {
			employee.setRole(emp.getRole());
		}
		return employeeRepo.save(employee);
	}
}