package com.deluxe.hrapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deluxe.hrapp.model.UserModel;
import com.deluxe.hrapp.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepo;

	public UserModel save(UserModel employee) {
		return userRepo.save(employee);
	}

	public UserModel findByemployeeId(Integer employeeId) {
		return userRepo.findById(employeeId);
	}
	
	public Integer findByUserPassword(String username, String password) {
		
		return userRepo.findByUserPassword(username, password);
	}
}