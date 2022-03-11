package com.techlearn.solid.srp;

import com.techlearn.antipattern.srm.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
public class UserValidator {

	public static boolean isValidUser(User user) {
		if(user.getFirstName() == null 
	   			|| user.getLastName() ==null
	   			|| user.getDob() == null) {
	   		return false;
	   	}
	   	return true;
	}
}
