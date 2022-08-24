package com.springboot.restapi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.restapi.entity.User;

@Service
public interface UserService 
{
	public List<User> getUsers();

	public User addUser(User user);

	public Optional<User> getUser(long userId);

	public Optional<User> deleteHardUser(long userId);

	public Optional<User> deleteSoftUser(long userId);

	public User updateUser(User user); 

	public List<User> sortByDobandJoiningDate();

	public List<User>findByuserFirstNameOruserLastNameOruserPincode(String userFirstName, String userLastName,
			String userPincode);





}
