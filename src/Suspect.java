import java.util.ArrayList;
import java.util.HashSet;

public class Suspect {
	
	private String name;
	private String codeName;
	private String country;
	private String city;	
	
	private ArrayList<String> phones = new ArrayList<String>();
	private ArrayList<Suspect> partnersOFsuspect = new ArrayList<Suspect>();
	private HashSet<Suspect> setSuggestedPartners = new HashSet<Suspect>();
	
	//Constructor with the arguments. Initializing the variables.
	public Suspect( String aName, String aNickname, String aCountry, String aCity ) {		
		name = aName;
		codeName = aNickname;
		country = aCountry;
		city = aCity;		
	}	
	
	// Methods get.
	public String getName() {
		return name;
	}
	
	public String getCodeName() {
		return codeName;
	}		
	
	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public ArrayList<String> getPhones() {
		return phones;
	}

	public ArrayList<Suspect> getPartnersOFsuspect() {
		return partnersOFsuspect;
	}
	
	public HashSet<Suspect> getSuggestedPartners(){
		
		createSuggestedPartners();
		
		return setSuggestedPartners;		
	}
	
	
	//Method which add the phone numbers in the list of each suspect.
	public void addNumber ( String number ) {		
		phones.add( number );		
	}
	
	//Method which add in the list of partners of each suspect. Before add each partner, first this method check if already exists the partner in list. 
	public void addPartner ( Suspect aSuspect ) {
		if(!partnersOFsuspect.contains( aSuspect ) ) {
			partnersOFsuspect.add( aSuspect );			
		}	
	}
	
	//Method which return true or false if suspect is connect with another suspect who is in the arguments.
	public boolean isConnectedTo ( Suspect aSuspect ) {
		if(partnersOFsuspect.contains(aSuspect))
			return true;
		return false;		
	}
	
	//Method which returns a list with suspects who is common partners of 2 suspects. Compares the list of 2 partners and return the commons partners.
	public ArrayList<Suspect> getCommonPartners( Suspect aSuspect ) {
		ArrayList<Suspect> commonSuspects = new ArrayList<Suspect>();
		
		for(Suspect common : partnersOFsuspect) {
			if(aSuspect.getPartnersOFsuspect().contains(common))
				commonSuspects.add(common);
		}
		
		return commonSuspects;
	}
	
	//Method which print the partners of the suspect and if the suspect is from the same country of partner after code name prints a ( * ).
	public void printPartners() {
		boolean fromSameCountry = false;
				
		for(Suspect suspect : partnersOFsuspect) {
			for(Suspect suspectToCompare : partnersOFsuspect) {
				if(suspect.getCountry().equalsIgnoreCase(suspectToCompare.getCountry()) && !suspect.equals(suspectToCompare)) {
					fromSameCountry = true;					
				}
				else
					fromSameCountry = false;
		    }
			if(fromSameCountry)
				System.out.println(suspect.getName() +"," +suspect.getCodeName() +"*");
			else
				System.out.println(suspect.getName() +"," +suspect.getCodeName());	    
		}		
	}
	
	public void createSuggestedPartners(){		
		
		for(Suspect partner : partnersOFsuspect) {
			setSuggestedPartners.addAll(partner.partnersOFsuspect);
		}
		setSuggestedPartners.removeAll(partnersOFsuspect);
		setSuggestedPartners.remove(this);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object forCompare) {
		Suspect otherSuspect = (Suspect) forCompare;
		return otherSuspect.getName().equals( this.getName() ) && otherSuspect.getCodeName().equals( this.getCodeName() );
	}
}
