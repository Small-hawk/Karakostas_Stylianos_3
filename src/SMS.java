//This class is a subclass in class Communication.
public class SMS extends Communication {
	
	private String text;	
	
	//Constructor with the arguments. Initializing the variables and call the constructor of superclass Communication.
	public SMS (String aCallerPhone, String aCalledPhone, int aYear, int aMonth, int aDate, String aText) {
		super(aCallerPhone, aCalledPhone, aYear, aMonth, aDate);
		text = aText;
	}
	
	//Method get.
	public String getText() {
		return text;
	}
	
	//Method get. This method is only because of abstract method in Communication. It's not used and for that reason return 0.
	public int getDuration() {
		return 0;
	}

	//Method for print the Information of objects from class SMS. This Method override the method with the same name in class Communication.
	public void printInfo() {
		System.out.println("This SMS has the following info");
		super.printInfo();
		System.out.println("Text: " +text);
	}

}
