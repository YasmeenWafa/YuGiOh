package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GUI extends JFrame{

	
	
	
	
	private PlayersPanel Player1;
	private PlayersPanel Player2;
	private ActionsPanel Actions;
	

	public GUI()
	{
		super("YuGiOh");
		//this.setTitle("el le3ba el gameela"); 
		
		setSize(2000, 700);
		
		this.setVisible(true);

		this.setLayout(new BorderLayout());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		  
		Player1 = new PlayersPanel();
		Player2 = new PlayersPanel();
		Actions = new ActionsPanel();
		
		this.add(Player1,BorderLayout.NORTH);
		
		this.add(Actions, BorderLayout.CENTER);
		
		
		this.add(Player2,BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.repaint();
		this.validate();
		
		
	}

	public ActionsPanel getActions() {
		return Actions;
	}

	public void setActions(ActionsPanel actions) {
		Actions = actions;
	}

	public PlayersPanel getPlayer1() {
		return Player1;
	}

	

	public PlayersPanel getPlayer2() {
		return Player2;
	}
	

	
	
	
}
