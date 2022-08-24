package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.restapi.Service.UserServiceImpl;
import com.springboot.restapi.dao.UserDao;
import com.springboot.restapi.entity.User;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserAppPoc1ApplicationTests {


	@Autowired
	private UserServiceImpl userServiceImpl;

	@MockBean
	private UserDao userDao;


	@Test
	public void getUsersTest() {
		when(userDao.findAll()).thenReturn(Stream
				.of(new User()).collect(Collectors.toList()));
		assertEquals(2,userServiceImpl.getUsers().size());		
	}

	@Test
	public void sortByDobandJoiningDateTest() {
		String sortBy="userDOB";
		String sortByDesc="userJoiningDate";
		String sortDirection="desc";

		Sort sortByuserDOB=sortDirection.equals(Sort.Direction.ASC.name())?
				Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

		Sort sortByuserJoiningDate=sortDirection.equals(Sort.Direction.ASC.name())?
				Sort.by(sortByDesc).ascending():Sort.by(sortByDesc).descending();

		Sort groupBySort=sortByuserDOB.and(sortByuserJoiningDate);

		List<User> user=userDao.findAll(groupBySort);

		user.forEach((i)->{
			System.out.println(i);
		});
	}

	@Test
	//get User By Id
	public void getUserTest() {
		long Id=10;
		when(userDao.findAll()).thenReturn(Stream
				.of(new User()).collect(Collectors.toList()));
		assertEquals(2,userServiceImpl.getUser(Id));		
	}

	@Test
	public void deletHardUserTest()
	{
		userDao.deleteById((long) 11);
		assertThat(userDao.existsById((long) 11)).isFalse();
	}

	@Test
	@Order(1)
	public void updateUserTest()throws ParseException
	{
		User user=new User();
		user.setUserId(11);
		user.setUserFirstName("pooja");
		user.setUserLastName("patel");
		user.setUserJoiningDate("12/08/2022");
		user.setUserDOB("20/09/2022");
		user.setUserPincode("10000");

		userDao.save(user);
		assertNotNull(userDao.findById((long) 11).get());

	}

}

