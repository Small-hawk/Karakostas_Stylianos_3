import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUISuspectPage extends JFrame {
		
	private JPanel panelSuspectPage;
	private JPanel suspectInfoPanel;
	private JPanel smsInfoPanel;
	private JPanel partnersInfoPanel;
	private JPanel toReturnSearchPanel;
	
	private JTextField suspectName;
	private JTextField suspectCodeName;
	private JTextField numberForSMSCommunication;
	
	private JList<String> phoneList;
	private JList<String> smsField;
	private JList<String> suspectPartners;
	private JList<String> suggestedPartners;
	private JList<String> sameCountry;
	
	private JButton findSMS;
	private JButton returnToSearch;
	
	private ArrayList<Suspect> suspectsGUI = new ArrayList<Suspect>();
	private ArrayList<String> phonesOfSuspectList = new ArrayList<String>();
	private ArrayList<SMS> smsList = new ArrayList<SMS>();
	private ArrayList<Suspect> partnersList = new ArrayList<Suspect>();
	private HashSet<Suspect> suggestedPartnersSet = new HashSet<Suspect>();
	private ArrayList<String> sameCountryList = new ArrayList<String>();
	private Suspect suspect;
	private Registry record;
	
	private DefaultListModel<String> phoneToView;
	private DefaultListModel<String> smsToView;
	private DefaultListModel<String> partnersToView;
	private DefaultListModel<String> suggestedPartnersToView;
	private DefaultListModel<String> sameCountryListToView;
	
	public GUISuspectPage(Registry record, String theName) {
		
		this.record = record;
		
		panelSuspectPage = new JPanel();
		suspectInfoPanel = new JPanel();
		smsInfoPanel = new JPanel();
		partnersInfoPanel = new JPanel();
		toReturnSearchPanel = new JPanel();
		
		suspectName = new JTextField();
		suspectName.setBorder(BorderFactory.createTitledBorder("The name of Suspect"));
		
		suspectCodeName = new JTextField();
		suspectCodeName.setBorder(BorderFactory.createTitledBorder("The nickname of Suspect"));
		
		numberForSMSCommunication = new JTextField("Enter a phone");
		numberForSMSCommunication.setColumns(18);
		
		phoneList = new JList<String>();
		phoneList.setBorder(BorderFactory.createTitledBorder("Suspect Phones"));
		
		smsField = new JList<String>();
		smsField.setBorder(BorderFactory.createTitledBorder("Suspicious SMS"));
		
		
		suspectPartners = new JList<String>();
		suspectPartners.setBorder(BorderFactory.createTitledBorder("Partners"));
		
		suggestedPartners = new JList<String>();
		suggestedPartners.setBorder(BorderFactory.createTitledBorder("Suggested Partners"));
		
		sameCountry = new JList<String>();
		sameCountry.setBorder(BorderFactory.createTitledBorder("Partners from same Country with Suspect"));
		
		findSMS = new JButton("Find SMS");
		returnToSearch = new JButton("Return to Search Screen");
		
		phoneToView = new DefaultListModel<String>();
		phoneList.setModel(phoneToView);
		JScrollPane spPhones = new JScrollPane(phoneList);
		
		smsToView = new DefaultListModel<String>();
		smsField.setModel(smsToView);
		JScrollPane spSMS = new JScrollPane(smsField);
		
		partnersToView = new DefaultListModel<String>();
		suspectPartners.setModel(partnersToView);
		JScrollPane spPartners = new JScrollPane(suspectPartners);
		
		suggestedPartnersToView = new DefaultListModel<String>();
		suggestedPartners.setModel(suggestedPartnersToView);
		JScrollPane spSuggested = new JScrollPane(suggestedPartners);
		
		sameCountryListToView = new DefaultListModel<String>();
		sameCountry.setModel(sameCountryListToView);
		JScrollPane spSame = new JScrollPane(sameCountry);
		
		GridLayout suspectInfoGrid = new GridLayout( 1 , 3 );		
		suspectInfoPanel.setLayout(suspectInfoGrid);
		suspectInfoPanel.add(suspectName);
		suspectInfoPanel.add(suspectCodeName);
		suspectInfoPanel.add(spPhones);		
		
		GridLayout smsInfoGrid = new GridLayout( 1 , 3 );
		smsInfoPanel.setLayout(smsInfoGrid);
		smsInfoPanel.add(numberForSMSCommunication);
		smsInfoPanel.add(spSMS);
		smsInfoPanel.add(findSMS);
		
		GridLayout partnersInfoGrid = new GridLayout( 3 , 1 );
		partnersInfoPanel.setLayout(partnersInfoGrid);
		partnersInfoPanel.add(spPartners);
		partnersInfoPanel.add(spSuggested);
		partnersInfoPanel.add(spSame);
		
		GridLayout toReturnSearchGrid = new GridLayout( 1 , 0 );
		toReturnSearchPanel.setLayout(toReturnSearchGrid);
		toReturnSearchPanel.add(returnToSearch);			
		
		BoxLayout box = new BoxLayout(panelSuspectPage , BoxLayout.PAGE_AXIS); 
		panelSuspectPage.setLayout(box);
		panelSuspectPage.add(suspectInfoPanel);
		panelSuspectPage.add(smsInfoPanel);
		panelSuspectPage.add(partnersInfoPanel);
		panelSuspectPage.add(toReturnSearchPanel);
		
		this.setContentPane(panelSuspectPage);
		
		ButtonListener listener = new ButtonListener();
		findSMS.addActionListener(listener);
		returnToSearch.addActionListener(listener);
		
		
		suspect = getSuspect(theName);
		phonesOfSuspectList.addAll(suspect.getPhones());
		
		suspectName.setText( suspect.getName() );
		
		suspectCodeName.setText( suspect.getCodeName() );
		
		for(String phone : phonesOfSuspectList)
			phoneToView.addElement(phone);
		
		partnersList.addAll(suspect.getPartnersOFsuspect());
		for(Suspect name : partnersList)
			partnersToView.addElement(name.getName());
		
		suggestedPartnersSet.addAll(suspect.getSuggestedPartners());
		for(Suspect name : suggestedPartnersSet)
			suggestedPartnersToView.addElement(name.getName());	
		
		sameCountryList.addAll( record.getSuspectsFromCountry( suspect.getCountry() ) );
		sameCountryListToView.addElement("Suspects coming from " +suspect.getCountry());
		for(String country : sameCountryList)
			sameCountryListToView.addElement(country);		
		
		this.setVisible(true);
		this.setSize(700, 900);
		this.setTitle("SuspectPage");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Suspect getSuspect(String aName) {
		
		Suspect theSuspect = null;
		suspectsGUI.addAll( record.listOfSuspects() );
		
		for(Suspect suspect : suspectsGUI) {
			if(suspect.getName().equalsIgnoreCase(aName) || suspect.getCodeName().equalsIgnoreCase(aName))
				theSuspect = suspect;
		}		
		return theSuspect;		
	}

	class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
						
			if(e.getSource() == findSMS) {
				String numberSMS = numberForSMSCommunication.getText();
				smsToView.removeAllElements();
				for(String phoneSuspect : phonesOfSuspectList) {
					smsList.addAll(record.getMessagesBetween(phoneSuspect, numberSMS));
				}
				for(SMS smsToAdd : smsList)
					smsToView.addElement(smsToAdd.getText());
				numberSMS = null;
				smsList.removeAll(smsList);
			}
			else if ( e.getSource() == returnToSearch) {
				new GUIFindSuspect(record);
				dispose();
			}					    
		}
	}
}
