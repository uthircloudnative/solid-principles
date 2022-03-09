# java-samples

## SOLID Design Principles

  S - **Single Responsibility**. A class/component is have only one reason to change.
  
  Lets assume we have following use case. 
    
     1. Handle User registration request. By validating given User Object.
     2. Create an entry in DB if User Object is Valid.
     3. Once User is created send Notification to User for activation.
     
 Below is **UserRegistration** class is defined with 3 different methods. All the steps are defined in same class.
 In future if there is another requirement like before sending notification we have to get the address for that User
 then we have to make changes to **UserRegistration** class. 
 
 ```
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

 ```
 
 Also this class is handling multiple tasks. We can define above class like below to satisfy **Single Responsibility**
 
 We can split Validation, User data creation, sending notification in seperate classes. Each class will do only one of this 
 task alone. 
 
 Also introduced **UserRegistrationController** class its main responsibility is handle User registration request and 
 orchestrate these 3 steps by calling/delegating respective classes.
 
 ```
 
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


public class UserRepository {
	
	public void createUser(User user) {
		//DB call is made and User record is created
	   	System.out.println("User record is created");
	}
}

public class NotificationService {

	public void sendNotification(User user) {
		
			//Sending email notification
	   		System.out.println("Send email to"+user.getEmailId());
		
	}
}
 ```
     
     
