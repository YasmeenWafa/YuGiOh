package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.listeners.Main;

public class HandPanel extends JPanel {

	private ArrayList<HandButtons> Hand;

	public HandPanel() {
		super();
		this.setLayout(new GridLayout(2,1));
		this.setPreferredSize(new Dimension(1000, 300));
		this.setLayout(new FlowLayout());
		
			Hand = new ArrayList<HandButtons>();
		
		/*for (int i = 0; i <Card.getBoard().getActivePlayer().getField().getHand().size(); i++) {
			HandButtons card = new HandButtons();
			card.setBackground(Color.YELLOW);
			Hand.add(card);
			Hand.get(i).setVisible(true);
			this.add(Hand.get(i));
		}*/
		
		this.setVisible(true);
		this.validate();
	}

	public ArrayList<HandButtons> getHand() {
		return Hand;
	}

	public void setHand(ArrayList<HandButtons> hand) {
		Hand = hand;
	}

}
