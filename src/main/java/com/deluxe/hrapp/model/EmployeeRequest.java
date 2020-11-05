package com.deluxe.hrapp.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Employee
 */




public class EmployeeRequest  extends Employee {
	
	@Override
    @JsonIgnore
	public void setId(Integer id) {
		super.setId(id);
	}
}

