package com.deluxe.hrapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deluxe.hrapp.model.EmployeeModel;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer>{
	
	EmployeeModel findById(Integer Id);
}