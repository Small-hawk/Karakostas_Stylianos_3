//This class is a subclass in class Communication.
public class PhoneCall extends Communication {
	
	private int duration;
	
	//Constructor with the arguments. Initializing the variables and call the constructor of superclass Communication.
	public PhoneCall(String aCallerPhone, String aCalledPhone, int aYear, int aMonth, int aDate, int aDuration) {
		super(aCallerPhone, aCalledPhone, aYear, aMonth, aDate);
		duration = aDuration;
	}	
	
	//Methods get.
	public int getDuration() {
		return duration;
	}
	
	//Method get. This method is only because of abstract method in Communication. It's not used and for that reason return null.
	public String getText() {
		return null;
		
	}
	
	//Method for print the Information of objects from class PhoneCall. This Method override the method with the same name in class Communication.
	public void printInfo() {
		System.out.println("This phone call has the following info");
		super.printInfo();
		System.out.println("Duration: " +duration);
	}
	
	
}
