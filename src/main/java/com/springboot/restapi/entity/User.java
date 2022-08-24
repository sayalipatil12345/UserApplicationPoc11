package com.springboot.restapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User 
{
	@Id
	private long userId;

	@NotEmpty
	@Size(min=4, message="UserFirstName must be min of 4 characters")
	private String userFirstName;

	@NotEmpty
	@Size(min=4, message="UserLastName must be min of 4 characters")
	private String userLastName;

	private String userDOB;

	private String userPincode;

	private String userJoiningDate;


}
