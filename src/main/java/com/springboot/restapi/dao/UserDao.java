package com.springboot.restapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.restapi.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>
{

	List<User> findByUserFirstNameOrUserLastNameOrUserPincode(String userFirstName, String userLastName, String userPincode);


}
