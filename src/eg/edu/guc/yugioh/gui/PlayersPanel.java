package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayersPanel extends JPanel{

	PlayersFieldPanel Field;
	PlayersToolsPanel Tools;
	HandPanel Hand;
	static int i = 1;

	

	


	

	
	
	
	public PlayersPanel ()

	{
		super();
		this.setPreferredSize(new Dimension(1000, 300));
		this.setLayout(new GridLayout(1,3));
		Field = new PlayersFieldPanel();
		Tools = new PlayersToolsPanel();
		Hand = new HandPanel();
		
	
		
		
		this.add(Field);
		this.add(Tools);
		this.add(Hand);
		
		
		
		/*if(i == 1)
		{
			this.add(Hand);
			this.add(Field);
			this.add(Tools);
			
		}*/
		//i++;
		this.setVisible(true);
		this.validate();
		
	}

	public HandPanel getHand() {
		return Hand;
	}

	public void setHand(HandPanel hand) {
		Hand = hand;
	}

	public PlayersFieldPanel getField() {
		return Field;
	}

	public PlayersToolsPanel getTools() {
		return Tools;
	}
	public int getI()
	{
		return i;
	}
	public void setI(int x){
	i = x;}
	
}
