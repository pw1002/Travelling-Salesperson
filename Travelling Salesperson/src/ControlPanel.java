import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ControlPanel {
	private JPanel panel;
	private JButton start;
	private JSpinner numOfCities;
	
	public ControlPanel() {
		panel = new JPanel(new FlowLayout());
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Initializing location for instructions JLabel using GridBag properties
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 10);
		
		JLabel instructions = new JLabel("Enter the number of cities:");
		mainPanel.add(instructions, gbc);
		
		//Initializing location for numOfCities JSpinner using GridBag properties
		gbc.gridx++;
		gbc.insets = new Insets(10, 0, 10, 10);
		
		//Creates JSpinner
		SpinnerNumberModel numMod = new SpinnerNumberModel(2, 2, 10, 1);
		numOfCities = new JSpinner(numMod);
		mainPanel.add(numOfCities, gbc);
		
		//Initializing location for start JButton using GridBag properties
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		start = new JButton("Start Simulation");
		mainPanel.add(start, gbc);
				
		panel.add(mainPanel);		
	}
	
	//returns panel JPanel
	public JPanel getPanel() {
		return panel;
	}
	
	//returns start JButton
	public JButton getButton() {
		return start;
	}
	
	//returns number of cities user entered
	public int getNumOfCities() {
		return (int)numOfCities.getValue();
	}

}
