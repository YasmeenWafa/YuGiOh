package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class PlayersToolsPanel extends JPanel{
	
	JLabel name;
    JLabel lifePoints;
    DeckButton Deck;
    GraveyardButton Graveyard;
    static int i = 1;
   
    
    public PlayersToolsPanel()
    {
    	super();
		this.setPreferredSize(new Dimension(2, 3));
		setLayout(new BorderLayout());
		
		this.setLayout(new GridLayout(2,2));
		
		name = new JLabel(JOptionPane.showInputDialog("Please enter name of player "+ i));
		
		name.setSize(100, 200);
		
		this.add(name);
		lifePoints = new JLabel("Life Points: ");
		this.add(lifePoints);
		Deck = new DeckButton("Deck");
		Deck.setBackground(java.awt.Color.CYAN);
		this.add(Deck);
		Graveyard = new GraveyardButton("Graveyard");
		Graveyard.setBackground(java.awt.Color.MAGENTA);
		this.add(Graveyard);

		i++;
		this.setVisible(true);
		this.validate();
    }

	
	public static int getI() {
		return i;
	}


	public static void setI(int i) {
		PlayersToolsPanel.i = i;
	}


	public JLabel getname() {
		return this.name;
	}


	public void setName(JLabel name) {
		this.name = name;
	}

	public JLabel getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(JLabel lifePoints) {
		this.lifePoints = lifePoints;
	}

	public JButton getDeck() {
		return Deck;
	}

	public void setDeck(DeckButton deck) {
		Deck = deck;
	}

	public JButton getGraveyard() {
		return Graveyard;
	}

	public void setGraveyard(GraveyardButton graveyard) {
		Graveyard = graveyard;
	}

}
