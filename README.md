# java-samples

# SOLID Design Principles

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
# Liskov's Substitution Principle

 When we are using subclass hierarchies derived classes must be completely substituable it's super class
 without breaking code.
 
 Ex.
 
   - If superclass method throws any exception then subclass must throw same exception or it should throw
     derived exception. If it throws any other type then its breaks this rule.
     
   - If the base class is defined with int positive member but in sub class require other negative member
     then its breaking this rule.
     
  - This priciple mainly insist importance of when to extend and when to use implements. It's mainly focus on
     when we define subtype by extending supertype then subtype must have all the behaviours of super type.
     
Lets assume we are designing a tool for Software Engineers to do their day today activities.This tool should help 
any engineer to do following use cases.

    1. Every engineer should be able to log their hours for their activities.
    2. Every engineer should be able to submit their performance goals.
    3. Development engineer should be able to assign code review to other engineer.
    4. Test Engineer can able to share their test scripts.
    5. Perf Engineer can able to provide performance review comments.
    
Lets create a base class Engineer with all this use cases as methods.

```

public abstract class Engineer {
	
	public abstract void logHours(float hours);
	public abstract void submitPerformanceGoals();
	public abstract void reviewCode();
	public abstract void shareTestScripts(String assignee);
	public abstract void givePerformanceReview(String assignee);
}

```
 Lets create a **DevelopmentEngineer**  which extend this base class.
 
```
public class DevlopmentEngineer extends Engineer{

	@Override
	public void logHours(float hours) {
		System.out.println("Log Hours");
	}

	@Override
	public void submitPerformanceGoals() {
		System.out.println("Submit Performance Goals");
		
	}

	@Override
	public void reviewCode() {
		System.out.println("Review Code");
		
	}

	@Override
	public void shareTestScripts(String assignee) {
		//Developer don't need to shareTest Scripts.
		 throw new UnsupportedOperationException();
		
	}

	@Override
	public void givePerformanceReview(String assignee) {
		//PerfEngineer don't need to givePerformanceReview.
		 throw new UnsupportedOperationException();
	}
}

```
 In the above **DevlopmentEngineer** we have to implement all the abstract methods. But methods **shareTestScripts()**
 and **givePerformanceReview()** not specific to developer responsibilities so we are throwing exception.
 
 But as per Base **Engineer** class this should have some valid behaviour logically. At this point it's breaking
 Liskov's Substitution principle. As we won't be able to replace Super class type with Subclass here from logical standpoint.
 
 We can avoid this by splitting these functions acorss multiple **interfaces** or **abstract clasess** by which we can make it 
 comply with Liskov's Substitution principle.
 
 ```
 public interface IEngineer {
	void logHours(float hours);
	void submitPerformanceGoals();
}

public interface IDevEngineer {
	void reviewCode();
}

public class DevlopmentEngineer implements IEngineer, IDevEngineer{

	@Override
	public void logHours(float hours) {
		System.out.println("logHours Implementation");
	}

	@Override
	public void submitPerformanceGoals() {
		System.out.println("submitPerformanceGoals implementation");
	}

	@Override
	public void reviewCode() {
		System.out.println("reviewCode implementation");
	}

}
 
 ```
 We have created two interfaces namely **IEngineer**, **IDevEngineer** in which **IEngineer** defines common functions of
 any Engineer and **IDevEngineer** defines functions only specific to Development Engineers.
 
 By doing this we don't have to worry about functionalities other than given user role like Test and Pef Engineer.
 This will comply with Liskov's Substitution principle.
 
# I - Interface Segregation Principle. A client should never forced to implement unused functions/methods. 

  When we expose an interface to a client we should have only methods intended for that clients need in such a way
  our interface design should be there.
  
  Let's assume we are designing a tool for a doctors with following use cases.
  
      1. A doctor can able to check a patient.
      2. A doctor should be able to prescribe medicine.
      3. A doctor can do/order surgery.
      
   Assume we have an interface called **Doctor** with all these 3 functions. And a **PrimaryCareProvider** class implements
   this **Doctor** interface.
   
```
public interface Doctor {
	void checkPatient();
	void prescribeMedicine();
	void orderSurgery();
}

public class PrimaryCareProvider implements Doctor{

	@Override
	public void checkPatient() {
		System.out.println("checkPatient implementation");
	}

	@Override
	public void prescribeMedicine() {
		System.out.println("prescribeMedicine implementation");
		
	}

	@Override
	public void orderSurgery() {
		// A Primary Care Doctor can't order for surgery. So this function is not needed
		//for PrimaryCareProvider but due to Doctor interface this class must implement it.
	}
}
```
   
   PrimaryCareProvider can't order surgery but this class is implementing **Doctor** interface it must implement 
   orderSurgery() this should be avoided.
   
   We can introduce two different interface **IDoctor** and **ISurgeon** each of which contains only specific methods relevant
   to their intended use case.
   
```
public interface IDoctor {

	void checkPatient();
	void prescribeMedicine();
}

public interface ISurgeon {
	void orderSurgery();
}

public class PrimaryCareProvider implements IDoctor{

	@Override
	public void checkPatient() {
		System.out.println("checkPatient implementation");
	}

	@Override
	public void prescribeMedicine() {
		System.out.println("prescribeMedicine implementation");
	}
}

public class Surgeon implements IDoctor, ISurgeon{

	@Override
	public void checkPatient() {
		System.out.println("checkPatient implementation");
	}

	@Override
	public void prescribeMedicine() {
		System.out.println("prescribeMedicine implementation");
	}

	@Override
	public void orderSurgery() {
		System.out.println("orderSurgery implementation");
	}
}
```
  From the above samples **PrimaryCareProvider** class will only implements **IDoctor** and implements only basic functions of
  PrimaryCareProvider.
  
  **Surgeon** will implement both **IDoctor** and **ISurgeon** and all the functions of both interface.
