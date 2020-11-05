package com.deluxe.hrapp.transformerUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.deluxe.hrapp.model.Employee;
import com.deluxe.hrapp.model.EmployeeModel;
import com.deluxe.hrapp.model.User;
import com.deluxe.hrapp.model.UserModel;

@Service
public class TransformerUtil {

	public EmployeeModel transformEmployee(Employee employee) {

		EmployeeModel employeeModel = new EmployeeModel();
		employeeModel.setAddress(employee.getAddress());
		employeeModel.setDob(employee.getDob());
		employeeModel.setName(employee.getName());
		employeeModel.setRole(employee.getRole());
		return employeeModel;
	}

	public List<Employee> transformEmployeeModelList(List<EmployeeModel> employee) {

		List<Employee> employeeList = new ArrayList<Employee>();

		if (!CollectionUtils.isEmpty(employee)) {
			for (EmployeeModel model : employee) {
				Employee emp = new Employee();
				emp.setId(model.getId());
				emp.setAddress(model.getAddress());
				emp.setDob(model.getDob());
				emp.setName(model.getName());
				emp.setRole(model.getRole());
				employeeList.add(emp);
			}
		}
		return employeeList;
	}

	public Employee transformEmployee(EmployeeModel model) {
		Employee emp = new Employee();

		if (null != model) {
			emp.setId(model.getId());
			emp.setAddress(model.getAddress());
			emp.setDob(model.getDob());
			emp.setName(model.getName());
			emp.setRole(model.getRole());
		}
		return emp;
	}
	
	public UserModel transformUser(User user) {

		UserModel userModel = new UserModel();
		userModel.setPassword(user.getPassword());
		userModel.setUsername(user.getUsername());
		return userModel;
	}

}