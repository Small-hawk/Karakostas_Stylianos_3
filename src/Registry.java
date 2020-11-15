import java.util.ArrayList;

// In this class there is all the registry / file from the records of suspects and communication.
public class Registry {	
	
	//In this list is all the suspects.
	private ArrayList<Suspect> suspects = new ArrayList<Suspect>();
	//In this list is all communication (objects from PhoneCall & SMS classes).
	private ArrayList<Communication> communications = new ArrayList<Communication>();  
	
	//Constructor with no arguments. There isn't variables for initializing.
	public Registry() {		
	}
	
	//This method adds the objects of class Suspect which created, in the List suspects of Registry.
	public void addSuspect(Suspect aSuspect) {
		suspects.add(aSuspect);
	}
	
	//This method adds the objects of class PhoneCall or SMS which created, in the List communications of Registry.
	//Also this method found in which suspect belongs the Phone and connect the suspects who have communicated and add the partners in each list of each suspect.
	public void addCommunication(Communication aCommunication) {
		communications.add(aCommunication);
						
		for(Suspect suspectCalled : suspects) {
			if(suspectCalled.getPhones().contains(aCommunication.getCalledPhone())) {
				for(Suspect suspectCaller : suspects) {
					if(suspectCaller.getPhones().contains(aCommunication.getCallerPhone())) {
						suspectCalled.addPartner(suspectCaller);
						suspectCaller.addPartner(suspectCalled);
					}					
				}				
			}			
		}		
	}
	
	//Method which return an object of class Suspect and give the suspect with the most partners. 
	//The object "SuspectWithMostPartners" created for to get the current object and for comparison reason, after the loop is the final choice and in the end is the object which return the method.
	// The variable most was used for buffer and comparison.
	public Suspect getSuspectWithMostPartners() {		
		Suspect SuspectWithMostPartners = new Suspect("", "", "", "");		
		int most = 0;
		
		for(Suspect suspect : suspects) {			
			if(suspect.getPartnersOFsuspect().size() > most) {
				SuspectWithMostPartners = suspect;
				most = suspect.getPartnersOFsuspect().size();
			}				
		}		
		return SuspectWithMostPartners;
	}
	
	//This method return an object PhoneCall which is the a call between 2 phone numbers with the longest duration. It needs the 2 numbers (2 Strings) as arguments.
	//First initialize the object and the variable which have created for buffer and comparison reasons. After that starts the loop for the list of communication.
	//While loop running in the first check (if) allowed only communications which are phone calls. After that begin the check to find the communications about the 2 numbers of arguments.
	//Provided that it saves the first data in the buffers and if the next communication of these 2 numbers have longest duration then replace with the new.
	//Finally return the longest Phone Call Between these 2 numbers.
	public PhoneCall getLongestPhoneCallBetween(String first, String second) {
		PhoneCall longestPhoneCallBetween = new PhoneCall("", "", 0, 0, 0, 0 );
		int duration = 0;
		
		for(Communication phoneCall : communications) {
			if(phoneCall.getClass().isInstance(longestPhoneCallBetween)) {
				if(phoneCall.getCalledPhone().contentEquals(first) || phoneCall.getCallerPhone().contentEquals(first)) {
					if(phoneCall.getCalledPhone().contentEquals(second) || phoneCall.getCallerPhone().contentEquals(second)) {
						if(phoneCall.getDuration() >  duration) {
						longestPhoneCallBetween = (PhoneCall) phoneCall;
						duration = phoneCall.getDuration();						
						}
					}
			    }
			}
		}		
		return longestPhoneCallBetween;
	}
	
	//This method return a List of messages (SMS) which contain specific words (like Gun, Explosives, Attack, Bomb). Also provides the information about when and from who to whom sent it the SMS.
	//First found the SMS which there are between the two phone numbers (arguments). 
	//When it founds that there is a communication between these numbers, check if there are dangerous words, if true put these communication SMS in the list which will return the method at last.  
	public ArrayList<SMS> getMessagesBetween(String first, String second) {
		ArrayList<SMS> messages = new ArrayList<SMS>();
		SMS sms = new SMS ("", "", 0, 0, 0, "");
		
		for(Communication communication : communications) {
			if(communication.getClass().isInstance(sms) ) {
				if(communication.getCalledPhone().contentEquals(first) || communication.getCallerPhone().contentEquals(first)) {
					if(communication.getCalledPhone().contentEquals(second) || communication.getCallerPhone().contentEquals(second)) {
						if(communication.getText().contains("Gun") || communication.getText().contains("Explosives") || communication.getText().contains("Attack") || communication.getText().contains("Bomb"))
							messages.add((SMS)communication);						
					}
				}
			}
		}
		return messages;
	}
	
	//This method print all the Suspects from a specific Country, which Country is the argument for this method.
	public void printSuspectsFromCountry(String aCountry) {
		
		for(Suspect suspect : suspects) {
			if(suspect.getCountry().equals(aCountry))
				System.out.println(suspect.getName() +" (" +suspect.getCodeName() +")");
			
		}
	}
	
	// Returns if the suspect exists. The search is based on name or nickname (ignore case).
	public boolean existsTheSuspect(String aName) {	
				
		for(Suspect suspect : this.suspects) {
			if(suspect.getName().equalsIgnoreCase(aName) || suspect.getCodeName().equalsIgnoreCase(aName))
				return true;
		}
		return false;
	}
	
	public ArrayList<Suspect> listOfSuspects() {
		return suspects;
	}
	
	public ArrayList<String> getSuspectsFromCountry(String theCountry){
		
		ArrayList<String> sameCountry = new ArrayList<String>(); 
		
		for(Suspect suspect : suspects) {
			if(suspect.getCountry().equals(theCountry))
				sameCountry.add(suspect.getName());
		}
		return sameCountry;				
	}
}
