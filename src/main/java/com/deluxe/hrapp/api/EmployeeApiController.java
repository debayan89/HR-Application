package com.deluxe.hrapp.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.deluxe.hrapp.exception.UnknownException;
import com.deluxe.hrapp.impl.EmployeeServiceImpl;
import com.deluxe.hrapp.jwtdecoder.JwtDecoder;
import com.deluxe.hrapp.model.Employee;
import com.deluxe.hrapp.model.EmployeeModel;
import com.deluxe.hrapp.model.EmployeeRequest;
import com.deluxe.hrapp.transformerUtil.TransformerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;

@Controller
public class EmployeeApiController implements EmployeeApi {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@Autowired
	private TransformerUtil transformerUtil;

    private static final Logger log = LoggerFactory.getLogger(EmployeeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EmployeeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> addemployee(@RequestHeader("authorization") String token , @ApiParam(value = "employee object that needs to be added to the HR App" ,required=true )  @Valid @RequestBody EmployeeRequest body) throws UnknownException{
       
    	if(!JwtDecoder.validateToken(token)) {
    		throw new UnknownException("Invalid Authorization Token In Header");
    	}
        
    	if(null == body) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EmployeeModel model = employeeService.save(transformerUtil.transformEmployee(body));
        log.info("Operation is successful", model);
        
        return new ResponseEntity<Integer>(model.getId(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Void> deleteemployee(@RequestHeader("authorization") String token, @ApiParam(value = "employee id to delete",required=true) @PathVariable("employeeId") Integer employeeId) throws UnknownException {
    	if(!JwtDecoder.validateToken(token)) {
    		throw new UnknownException("Invalid Authorization Token In Header");
    	}
    	if(null == employeeId) {
        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    	log.info("Operation is ", employeeId);
        employeeService.deleteByemployeeId(employeeId);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Employee>> findAllEmployee(@RequestHeader("authorization") String token) throws UnknownException {   	
    	if(!JwtDecoder.validateToken(token)) {
    		throw new UnknownException("Invalid Authorization Token In Header");
    	}
        return new ResponseEntity<List<Employee>>(
        		transformerUtil.transformEmployeeModelList(employeeService.findAllEmployeeList()), HttpStatus.OK);
    }

    public ResponseEntity<Employee> getemployeeById(@RequestHeader("authorization") String token, @ApiParam(value = "ID of employee to return",required=true) @PathVariable("employeeId") Integer employeeId) throws UnknownException {
    	if(!JwtDecoder.validateToken(token)) {
    		throw new UnknownException("Invalid Authorization Token In Header");
    	}
    	if(null == employeeId) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Employee>(
        		transformerUtil.transformEmployee(employeeService.findByemployeeId(employeeId)), HttpStatus.OK);
    }

    public ResponseEntity<Employee> updateemployee(@RequestHeader("authorization") String token, @ApiParam(value = "employee object that needs to be updated to the HR App" ,required=true )  @Valid @RequestBody EmployeeRequest body ,@PathVariable("employeeId") Integer employeeId) throws UnknownException {
    	if(!JwtDecoder.validateToken(token)) {
    		throw new UnknownException("Invalid Authorization Token In Header");
    	}
    	if(null == body) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Employee>(transformerUtil.transformEmployee(employeeService.updateEmployeeById(employeeId, transformerUtil.transformEmployee(body))),HttpStatus.OK);
    }


}