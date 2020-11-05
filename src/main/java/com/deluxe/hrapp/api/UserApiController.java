package com.deluxe.hrapp.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.deluxe.hrapp.impl.UserServiceImpl;
import com.deluxe.hrapp.model.User;
import com.deluxe.hrapp.model.UserModel;
import com.deluxe.hrapp.transformerUtil.TransformerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-04T20:26:35.102Z")

@Controller
public class UserApiController implements UserApi {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private TransformerUtil transformerUtil;

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User body) {
    	if(null == body) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserModel model = userService.save(transformerUtil.transformUser(body));
        log.info("Operation is successful", model);
        return new ResponseEntity<Integer>(model.getId(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> loginUser(@NotNull @ApiParam(value = "The user name for login", required = true) @Valid @RequestParam(value = "username", required = true) String username,@NotNull @ApiParam(value = "The password for login in clear text", required = true) @Valid @RequestParam(value = "password", required = true) String password) {
    	Integer id = userService.findByUserPassword(username, password);
    	
    	if(null == id) {
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	}
        return new ResponseEntity<String>(Integer.toString(id), HttpStatus.OK);
    }

    public ResponseEntity<Void> logoutUser() {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}