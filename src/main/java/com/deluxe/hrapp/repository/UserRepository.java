package com.deluxe.hrapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deluxe.hrapp.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	
	UserModel findById(Integer Id);
	
	@Query(value = "SELECT * FROM user u WHERE u.username = :username and u.password = :password", nativeQuery = true)
	Integer findByUserPassword(@Param("username") String username,@Param("password") String password);
}