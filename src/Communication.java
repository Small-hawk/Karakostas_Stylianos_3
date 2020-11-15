// Class Communication is a superclass and has 2 subclass (PhoneCall and SMS). This class is abstract.
public abstract class Communication {
	
	private String callerPhone;
	private String calledPhone;
	private int year;
	private int month;
	private int date;
	
	//Constructor with the arguments. Initializing the variables.
	public Communication(String aCallerPhone, String aCalledPhone, int aYear, int aMonth, int aDate) {
		callerPhone = aCallerPhone;
		calledPhone = aCalledPhone;
		year = aYear; 
		month = aMonth;
		date = aDate;		
	}	
	
	//Methods get.
	public String getCallerPhone() {
		return callerPhone;
	}

	public String getCalledPhone() {
		return calledPhone;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDate() {
		return date;
	}
	
	//This 2 abstract methods is for the 2 subclasses, and for for some easy-use in the class Registry.
	public abstract int getDuration();
	
	public abstract String getText();
	
	//This method for print, is used in the two subclasses where this method override in the subclasses also for more specific print. 
	public void printInfo() {
		System.out.println("Between " +callerPhone +" --- " +calledPhone);
		System.out.println("on " +year +"/" +month +"/" +date);		
	}
	

}
