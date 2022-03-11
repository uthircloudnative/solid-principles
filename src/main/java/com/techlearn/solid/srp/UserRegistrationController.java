package com.techlearn.solid.srp;

import com.techlearn.antipattern.srm.User;

/**
 * @author Uthiraraj Saminathan
 *
 */
public class UserRegistrationController {
	
	private UserRepository userRepository;
	private NotificationService notificationService;
	
	public UserRegistrationController(UserRepository userRepository,
			NotificationService notificationService) {
		this.userRepository = userRepository;
		this.notificationService = notificationService;
	}

	public void handleRegistration(User user) {
		
		boolean isValidUser = UserValidator.isValidUser(user);
		if(isValidUser) {
			userRepository.createUser(user);
			notificationService.sendNotification(user);
		}else {
			//Throw exception
		}
	}

}
