package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.Card;

public class ActionsPanel extends JPanel

{

	private ActionButton endTurn;
	private ActionButton endPhase;
	/*private ActionButton setMonster;
	private ActionButton summonMonster;
	private ActionButton activateSpell;
	private ActionButton setSpell;
	private ActionButton switchMonster;
	private ActionButton declareAttack;*/
	private JLabel Phase;
	private ArrayList<ActionButton> Actions;
	public ActionsPanel()
	{
		super();
		this.setPreferredSize(new Dimension(1, 1));
		this.setLayout(new GridLayout(3,1));
		Actions = new ArrayList<ActionButton>(8);
		endTurn = new ActionButton("End Turn");
		endTurn.setBackground(Color.ORANGE);
		
		endPhase = new ActionButton("End Phase");
		endPhase.setBackground(Color.ORANGE);
		/*setMonster = new ActionButton("Set Monster");
		summonMonster = new ActionButton("Summon Monster");
		activateSpell = new ActionButton("Activate Spell");
		setSpell = new ActionButton("Set Spell");
		switchMonster = new ActionButton("Switch Monster");
		declareAttack = new ActionButton("ATTACK");*/
		Phase = new JLabel("Phase: ");
		this.add(Phase);
		Actions.add(endTurn);
		Actions.add(endPhase);
		/*Actions.add(switchMonster);
		Actions.add(summonMonster);
		Actions.add(activateSpell);
		Actions.add(setSpell);
		Actions.add(setMonster);
		Actions.add(declareAttack);*/
		for(int i = 0; i<Actions.size();i++)
		{
			this.add(Actions.get(i));
		}
		this.setVisible(true);
		this.repaint();
		this.validate();
	}
	public JLabel getPhase() {
		return Phase;
	}
	public void setPhase(String phase) {
		Phase.setText("Phase: "+phase);
	}
	public ActionButton getEndTurn() {
		return endTurn;
	}
	public void setEndTurn(ActionButton endTurn) {
		this.endTurn = endTurn;
	}
	public ActionButton getEndPhase() {
		return endPhase;
	}
	public void setEndPhase(ActionButton endPhase) {
		this.endPhase = endPhase;
	}
	/*public ActionButton getSetMonster() {
		return setMonster;
	}
	public void setSetMonster(ActionButton setMonster) {
		this.setMonster = setMonster;
	}
	public ActionButton getSummonMonster() {
		return summonMonster;
	}
	public void setSummonMonster(ActionButton summonMonster) {
		this.summonMonster = summonMonster;
	}
	public ActionButton getActivateSpell() {
		return activateSpell;
	}
	public void setActivateSpell(ActionButton activateSpell) {
		this.activateSpell = activateSpell;
	}
	public ActionButton getSetSpell() {
		return setSpell;
	}
	public void setSetSpell(ActionButton setSpell) {
		this.setSpell = setSpell;
	}
	public ActionButton getSwitchMonster() {
		return switchMonster;
	}
	public void setSwitchMonster(ActionButton switchMonster) {
		this.switchMonster = switchMonster;
	}
	public ActionButton getDeclareAttack() {
		return declareAttack;
	}
	public void setDeclareAttack(ActionButton declareAttack) {
		this.declareAttack = declareAttack;
	}*/
}
