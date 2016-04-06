package eg.edu.guc.yugioh.gui;

import javax.swing.JButton;

import eg.edu.guc.yugioh.listeners.Main;

public class HandButtons extends JButton { 
	
	
	public HandButtons(){ 
		super();
		this.addActionListener(Main.getC());
	}

}
