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
 
 # O - Open / Close Pricipal -> Open for extension closed for modification.
 
     - A class or componenet is open for extension and closed for modfication.
     
     - A new functionality should be added without affecting/modifiying existing class or module.
     
     - By adding new feature without modofying existing component we can avoid breaking existing functionality.
     
Let's assume following User case and it's implementation is **Open/Close Principle.**

     1. Design a component which should generate different type of reports for given data.
     
     2. Ex types it should support is PDF, CVS, TEXT.
     
We can code this in multiple ways. Quickest and easiest way is based on given type we can implement different
report generation logic in a single class with if else loop as like below.

```
public class ReportGenarateService {
	
	public void generateReport(List<User> users, String reportFormat) {
		
		if("PDF".equals(reportFormat)) {
			//Create PDF Report and Store
		}else if("CSV".equals(reportFormat)) {
			//Create CSV Report and Store.
		}else {
			//Create txt Report and Store
		}
	}
}

```
 Problem with this approach is in future if we need to introduce another report type like png report then
 we have to modify existing report implementation.
 
 By following Opne/Close principle we can avoid this modification instead we can design our components
 in a way we can introduce or add new functionality without affecting existing one.
 
 Let's introduce a **interface** with method **generateReport()** and have corresponding implementation
 classes for different type of reports which implement this interface as like below.
 
 ```
 public interface ReportGenerator {
	void generateReport(List<User> user);
}

public class CSVReportGenarator implements ReportGenerator {

	@Override
	public void generateReport(List<User> user) {
		// Create CSV Report
	}
}

public class PDFReportGenerator implements ReportGenerator{

	@Override
	public void generateReport(List<User> user) {
		//Genarate PDF report
		
	}
}

public class TextReportGenarator implements ReportGenerator{

	@Override
	public void generateReport(List<User> user) {
		//Create text report
	}
}
 ```

 Now we have seperate Classes for each and every reprot format. In future if a new report type is introduced
 we no need to touch any current implementations.
 
 Now we have to call corresponding Report implementation classes based on given type. For that we can use a
 seperate class based on **Factory Pattern**. This class will identify given report type and create corresponding
 report instance for consumer.
 
```
public class ReportGenaratorFactory {

	public ReportGenerator generateReport(String formatType) {
		
		if("PDF".equals(formatType)) {
			return new PDFReportGenerator();
		}else if("CSV".equals(formatType)){
			return new CSVReportGenarator();
		}else {
			return new TextReportGenarator();
		}
	}
}
```
 Any client class will call this Factory by passing corresponding report type and get the correct report generation
 instance and execute it using its interface reference instead of actual implementation.
 
```
public class ReportGenarateReqHandler {
	
	public static void main(String[] args) {
		
		ReportGenaratorFactory reportFactory = new ReportGenaratorFactory();
		
		ReportGenerator pdfReportGenarator = reportFactory.generateReport("PDF");
		
		pdfReportGenarator.generateReport(Arrays.asList(new User("TestF","TestLast",
				                                                 LocalDate.now(),"testocp@gmail.com")));
		
	}
}
```
