package com.springboot.restapi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.restapi.dao.UserDao;
import com.springboot.restapi.entity.User;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;


	@Override
	public List<User> getUsers() {

		return this.userDao.findAll();
	}

	@Override
	public Optional<User> getUser(long userId)
	{
		return this.userDao.findById(userId);
	}

	public User addUser(User user) {
		userDao.save(user);
		return user;
	}

	@Override
	public Optional<User> deleteHardUser(long userId) {
		userDao.deleteById(userId);

		return null;
	}
	
	
	@Override
	public Optional<User> deleteSoftUser(long userId) {

		return null;
	}
	
	
	@Override
	public User updateUser(User user ) {
		User update=this.userDao.findById(user.getUserId()).orElse(null);
		update.setUserFirstName(user.getUserFirstName());
		update.setUserLastName(user.getUserLastName());
		update.setUserJoiningDate(user.getUserJoiningDate());
		update.setUserDOB(user.getUserDOB());
		update.setUserPincode(user.getUserPincode());
		userDao.save(update);
		return update ;
	}
	

	@Override
	public List<User> sortByDobandJoiningDate() {
		return userDao.findAll(Sort.by("userDOB").descending().and(Sort.by("userJoiningDate").descending()));
	}
	
	
	@Override
	public List<User> findByuserFirstNameOruserLastNameOruserPincode(String userFirstName, String userLastName,
			String userPincode) {
		// TODO Auto-generated method stub
		return userDao.findByUserFirstNameOrUserLastNameOrUserPincode(userFirstName, userLastName, userPincode);
	}

}


