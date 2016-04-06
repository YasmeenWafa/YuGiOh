package eg.edu.guc.yugioh.gui;

import javax.swing.JOptionPane;

public class showWindow {

	String choice;
	
	public showWindow(String message)
	
	{
		choice = JOptionPane.showInputDialog(message);
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	
}
