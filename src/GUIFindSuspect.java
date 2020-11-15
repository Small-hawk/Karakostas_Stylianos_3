import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIFindSuspect extends JFrame {	
	
	private JPanel panelFindSuspect;
	
	private JTextField suspectsName;
	
	private JButton find;
	
	private Registry record;
	
	
	public GUIFindSuspect(Registry record) {
		
		this.record = record;
		panelFindSuspect = new JPanel();
		
		suspectsName = new JTextField("Please enter suspects name");
		
		find = new JButton("Find");
		
		panelFindSuspect.add(suspectsName);
		panelFindSuspect.add(find);	
		
		this.setContentPane(panelFindSuspect);
		
		ButtonListener listener = new ButtonListener();		
		find.addActionListener(listener);
		
		this.setVisible(true);
		this.setSize(300, 150);
		this.setTitle("Find Suspect");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == find) {
				
				String suspectName = suspectsName.getText();
							
				if(record.existsTheSuspect(suspectName)) {
					new GUISuspectPage(record, suspectName);
					dispose();
				}					
				else
					JOptionPane.showMessageDialog(panelFindSuspect, "Suspect " + suspectName + " not found");
			}
		}
	}
}
