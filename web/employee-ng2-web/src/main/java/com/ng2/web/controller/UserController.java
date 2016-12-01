package com.ng2.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ng2.web.domain.User;
import com.ng2.web.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User user){
		LOG.info("Starting to save the user.");
		user = userRepository.save(user);
		LOG.info("User with id: {} saved successfully",user.getId());
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	
	@RequestMapping(path="{id}",consumes=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id")Long id, @RequestBody User user){
		LOG.info("Starting to update the user.");
		user.setId(id);
		user = userRepository.save(user);
		LOG.info("User with id: {} updated successfully",user.getId());
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path="{id}", method=RequestMethod.GET)
	public ResponseEntity<User> findOneUser(@PathVariable("id")Long id){
		LOG.info("Starting to fetch a user with id {}.",id);
		User user = userRepository.findOne(id);
		LOG.info("Found user with id: {} ",user.getId());
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(path="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteOneUser(@PathVariable("id")Long id){
		LOG.info("Starting to delete a user with id {}.",id);
		userRepository.delete(id);
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser(){
		LOG.info("Starting to list all user ");
		List<User> users = userRepository.findAll();
		LOG.info("Found users size is: {} ",users.size());
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
}
