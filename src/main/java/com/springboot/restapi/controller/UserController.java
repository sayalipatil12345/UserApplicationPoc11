package com.springboot.restapi.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.Service.UserService;
import com.springboot.restapi.entity.User;

@RestController
public class UserController 
{
	@Autowired
	private UserService userService;


	@GetMapping("/users/getAll")
	public List<User> getUsers()
	{
		return this.userService.getUsers();
	}

	@GetMapping("/users/sortByDOBandJoiningDate")
	public List<User> sortByDobandJoiningDate()
	{
		return this.userService.sortByDobandJoiningDate();
	}
	

	@GetMapping("/users/{userId}")
	public Optional<User> getUser(@PathVariable String userId)
	{
		return this.userService.getUser(Long.parseLong(userId));
	}

	@PostMapping("/users/addAll")
	public User addUser(@Valid @RequestBody User user)
	{
		return this.userService.addUser(user);
	}

	@GetMapping("/users/info")
	public ResponseEntity<List<User>> getUserByuserFirstNameOruserLastNameOruserPincode(@RequestParam String userFirstName,
			@RequestParam String userLastName,
			@RequestParam String userPincode)
	{
		return new ResponseEntity<> (userService.findByuserFirstNameOruserLastNameOruserPincode(userFirstName, userLastName, userPincode), HttpStatus.OK);
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,@PathVariable int userId)
	{
		User updateUser=this.userService.updateUser(user);
		return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}


	@DeleteMapping("/users/{userIdHardDelete}")
	public Optional<User> deleteHardUser(@PathVariable String userIdHardDelete) {
		this.userService.deleteHardUser(Long.parseLong(userIdHardDelete));
		return Optional.empty();

	}

	@PutMapping("/usersSoft/{userIdSoftDelete}")
	public String deleteSoftUser(@PathVariable String userIdSoft) {
		String success = null;
		List<User> user = this.userService.getUsers();

		Iterator<User> itr = user.iterator();            
		while(itr.hasNext()){
			String name = Long.toString(itr.next().getUserId());
			if(name.equals(userIdSoft)){
				itr.remove();
				success = ("Record Soft Deleted for User ID " +userIdSoft);
			}
		}
		user.forEach(System.out::println);
		return success;

	}
}


