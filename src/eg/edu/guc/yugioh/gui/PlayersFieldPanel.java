package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;



public class PlayersFieldPanel extends JPanel {
	private ArrayList<MonsterButton> MonstersArea;
	private ArrayList<SpellButton> SpellArea;
	//private HandPanel Hand;
	
	
	Board board = Card.getBoard();
	
	
	public PlayersFieldPanel(){
		super();
		this.setPreferredSize(new Dimension(600, 300));
		setLayout(new BorderLayout());
		
		
		this.setLayout(new GridLayout(2,7));
		MonstersArea = new ArrayList<MonsterButton>(5);
		SpellArea = new ArrayList<SpellButton>(5);
		
		//Hand = new HandPanel();
		//Hand.setVisible(false);
		//this.add(Hand);
		
		JLabel MonsterArea = new JLabel("Monster Area");
		this.add(MonsterArea);
		
		
		
		for (int i=0; i<5;i++) {
			MonsterButton monsterButton= new MonsterButton();
			monsterButton.setBackground(Color.BLUE);
			MonstersArea.add(monsterButton);
			MonstersArea.get(i).setVisible(true);
			//MonstersArea.get(i).setName("Monster" + (i+1));
			this.add(MonstersArea.get(i));
			
			
		}
	
	

		
		JLabel SpellsArea = new JLabel("Spell Area");
		this.add(SpellsArea);
		for(int i = 0; i<5;i++)
		{
		SpellButton spellButton = new SpellButton();
		spellButton.setBackground(Color.GREEN);
			SpellArea.add(spellButton);
			SpellArea.get(i).setVisible(true);
			//SpellArea.get(i).setName("Spell" + (i+1));
			this.add(SpellArea.get(i));
			
		}
		
		
		this.setVisible(true);
		this.validate();
	}


	public ArrayList<MonsterButton> getMonstersArea() {
		return MonstersArea;
	}

	public ArrayList<SpellButton> getSpellArea() {
		return SpellArea;
	}
	}
