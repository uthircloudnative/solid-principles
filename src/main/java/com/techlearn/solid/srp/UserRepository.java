package com.techlearn.solid.srp;

import com.techlearn.antipattern.srm.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
public class UserRepository {
	
	public void createUser(User user) {
		//DB call is made and User record is created
	   	System.out.println("User record is created");
	}
}
