package com.techlearn.solid.srp;

import com.techlearn.antipattern.srm.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
public class NotificationService {

	public void sendNotification(User user) {
		
			//Sending email notification
	   		System.out.println("Send email to"+user.getEmailId());
		
	}
}
