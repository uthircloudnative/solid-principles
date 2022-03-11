package com.techlearn.antipattern.srm;
/**
 * @author Uthiraraj Saminathan
 *
 */
public class UserRegistration {

	public boolean isValidUser(User user) {
		
		if(user.getFirstName() == null 
				|| user.getLastName() ==null
				|| user.getDob() == null) {
			return false;
		}
		return true;
	}
	
	public void createUser(User user) {
		//DB call is made and User record is created
		System.out.println("User record is created");
	}
	
	public void sendNotification(User user) {
		if(user.getEmailId() != null) {
			//Sending email notification
			System.out.println("Send email");
		}
	}
}
