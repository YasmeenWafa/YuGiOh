package eg.edu.guc.yugioh.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.ActionButton;
import eg.edu.guc.yugioh.gui.DeckButton;
import eg.edu.guc.yugioh.gui.GUI;
import eg.edu.guc.yugioh.gui.GraveyardButton;
import eg.edu.guc.yugioh.gui.HandButtons;
import eg.edu.guc.yugioh.gui.MonsterButton;
import eg.edu.guc.yugioh.gui.SpellButton;

public class Controller implements ActionListener {

	static GUI gui;
	JButton click1;
	JButton click2;
	JButton click3;
	JButton click4;
	JButton click5;

	static Board board;
	static Player Player1;
	static Player Player2;

	ActionButton summonMonster;

	ActionButton activateSpell;

	ActionButton setSpell;

	ActionButton switchMonster;

	ActionButton declareAttack;
	ActionButton setMonster;

	public Controller(Board b, GUI Gui) throws MissingFieldException,
			UnknownCardTypeException, UnknownSpellCardException,
			EmptyFieldException, IOException {

		board = b;
		gui = Gui;
		Player1 = new Player(gui.getPlayer1().getTools().getname().getText());
		Player2 = new Player(gui.getPlayer2().getTools().getname().getText());
		board.startGame(Player1, Player2);

		summonMonster = new ActionButton("Summon Monster");
		activateSpell = new ActionButton("Activate Spell");
		setSpell = new ActionButton("Set Spell");
		switchMonster = new ActionButton("Switch Monster");
		declareAttack = new ActionButton("ATTACK");
		setMonster = new ActionButton("Set Monster");
		fillButtons();

		Control();
		addActionListenersToButtons();

	}

	private void fillButtons() {
		// TODO Auto-generated method stub
		ArrayList<MonsterButton> monsters1 = gui.getPlayer1().getField()
				.getMonstersArea();
		ArrayList<SpellButton> spells1 = gui.getPlayer1().getField()
				.getSpellArea();
		ArrayList<MonsterButton> monsters2 = gui.getPlayer2().getField()
				.getMonstersArea();
		ArrayList<SpellButton> spells2 = gui.getPlayer2().getField()
				.getSpellArea();

		ArrayList<HandButtons> hand1 = gui.getPlayer1().getHand().getHand();
		ArrayList<HandButtons> hand2 = gui.getPlayer2().getHand().getHand();

		for (int i = 0; i < 5; i++) {

			monsters1.get(i).setText("Monster " + (i + 1));
			monsters1.get(i).setVisible(true);
		}

		for (int i = 0; i < 5; i++) {
			spells1.get(i).setText("Spell " + (i + 1));
			spells1.get(i).setVisible(true);
		}

		for (int i = 0; i < 5; i++) {
			monsters2.get(i).setText("Monster " + (i + 1));
			monsters2.get(i).setVisible(true);
		}

		for (int i = 0; i < 5; i++) {
			spells2.get(i).setText("Spell " + (i + 1));
			spells2.get(i).setVisible(true);
		}

	}

	private void addActionListenersToButtons() {

		int monstersSize1 = gui.getPlayer1().getField().getMonstersArea()
				.size();
		for (int i = 0; i < monstersSize1; i++) {
			if (gui.getPlayer1().getField().getMonstersArea().get(i) instanceof JButton) {
				gui.getPlayer1().getField().getMonstersArea().get(i)
						.addActionListener(this);

			}
		}
		int SpellsSize1 = gui.getPlayer1().getField().getSpellArea().size();
		for (int i = 0; i < SpellsSize1; i++) {
			if (gui.getPlayer1().getField().getSpellArea().get(i) instanceof JButton) {
				gui.getPlayer1().getField().getSpellArea().get(i)
						.addActionListener(this);

			}
		}

		int monstersSize2 = gui.getPlayer2().getField().getMonstersArea()
				.size();
		for (int i = 0; i < monstersSize2; i++) {
			if (gui.getPlayer2().getField().getMonstersArea().get(i) instanceof MonsterButton) {
				gui.getPlayer2().getField().getMonstersArea().get(i)
						.addActionListener(this);

			}
		}
		int SpellsSize2 = gui.getPlayer2().getField().getSpellArea().size();
		for (int i = 0; i < SpellsSize2; i++) {
			if (gui.getPlayer2().getField().getSpellArea().get(i) instanceof SpellButton) {
				gui.getPlayer2().getField().getSpellArea().get(i)
						.addActionListener(this);

			}
		}
		// gui.getActions().getSetMonster().addActionListener(this);
		// gui.getActions().getSummonMonster().addActionListener(this);
		// gui.getActions().getSetSpell().addActionListener(this);
		// gui.getActions().getActivateSpell().addActionListener(this);
		gui.getActions().getEndTurn().addActionListener(this);
		gui.getActions().getEndPhase().addActionListener(this);
		// gui.getActions().getSwitchMonster().addActionListener(this);
		// gui.getActions().getDeclareAttack().addActionListener(this);

		gui.getPlayer1().getTools().getDeck().addActionListener(this);
		gui.getPlayer1().getTools().getGraveyard().addActionListener(this);

		gui.getPlayer2().getTools().getDeck().addActionListener(this);
		gui.getPlayer2().getTools().getGraveyard().addActionListener(this);

		// ACTION LISTENER TO HAND

		for (int i = 0; i < gui.getPlayer1().getHand().getHand().size(); i++) {
			gui.getPlayer1().getHand().getHand().get(i).addActionListener(this);

		}

		for (int i = 0; i < gui.getPlayer2().getHand().getHand().size(); i++) {
			gui.getPlayer2().getHand().getHand().get(i).addActionListener(this);

		}

		setMonster.addActionListener(this);
		summonMonster.addActionListener(this);
		switchMonster.addActionListener(this);
		declareAttack.addActionListener(this);
		activateSpell.addActionListener(this);
		setSpell.addActionListener(this);

	}

	public void updateLifePoints() {
		gui.getPlayer1().getTools().getLifePoints()
				.setText("Life Points: " + Player1.getLifePoints());
		gui.getPlayer2().getTools().getLifePoints()
				.setText("Life Points: " + Player2.getLifePoints());
		gui.getPlayer1().getTools().getLifePoints().setForeground(Color.BLUE);
		gui.getPlayer2().getTools().getLifePoints().setForeground(Color.BLUE);

		gui.validate();
	}

	public void showWinner() throws MissingFieldException,
			UnknownCardTypeException, UnknownSpellCardException,
			EmptyFieldException, IOException {
		if (board.getWinner() != null) {
			JOptionPane.showMessageDialog(null, "The Winner is: "
					+ board.getWinner().getName());

			String s = JOptionPane.showInputDialog(null,
					"Do you want to play again? ");
			if (s.equalsIgnoreCase("ok") || s.equalsIgnoreCase("Yes")) {

				gui.getPlayer1().getTools().setI(1);
				;
				gui.getPlayer2().getTools().setI(1);
				new Main();

				gui.validate();
			} else {
				JOptionPane.showMessageDialog(null, "Happy Duelling");
				System.exit(0);
			}
		}

	}

	public void updatePhase() {
		gui.getActions().setPhase(
				"" + board.getActivePlayer().getField().getPhase());
		gui.getActions().getPhase().setForeground(Color.ORANGE);
		gui.validate();
	}

	public void updateDeck() {

		gui.getPlayer1()
				.getTools()
				.getDeck()
				.setText(
						"Deck" + "("
								+ Player1.getField().getDeck().getDeck().size()
								+ ")");
		gui.getPlayer2()
				.getTools()
				.getDeck()
				.setText(
						"Deck" + "("
								+ Player2.getField().getDeck().getDeck().size()
								+ ")");
		gui.validate();
	}

	public void changeHands() {
		if (Player1 == getBoard().getActivePlayer()) {
			gui.getPlayer1().getHand().setVisible(true);
			gui.getPlayer2().getHand().setVisible(false);
		} else {
			gui.getPlayer2().getHand().setVisible(true);
			gui.getPlayer1().getHand().setVisible(false);
		}

		gui.validate();
	}

	public void updateMonstersArea() {
		ImageIcon back = new ImageIcon("Card Back.png");
		if (!Player1.getField().getMonstersArea().isEmpty()) {
			for (int i = 0; i < Player1.getField().getMonstersArea().size(); i++) {
				gui.getPlayer1()
						.getField()
						.getMonstersArea()
						.get(i)
						.setText(
								Player1.getField().getMonstersArea().get(i)
										.getName());
				if (Player1.getField().getMonstersArea().get(i).getMode()
						.equals((Mode.ATTACK))) {
					gui.getPlayer1().getField().getMonstersArea().get(i)
							.setBackground(Color.RED);
				} else {
					gui.getPlayer1().getField().getMonstersArea().get(i)
							.setBackground(Color.GRAY);

				}
			}
		}
		if (!Player2.getField().getMonstersArea().isEmpty()) {
			for (int i = 0; i < Player2.getField().getMonstersArea().size(); i++) {
				gui.getPlayer2()
						.getField()
						.getMonstersArea()
						.get(i)
						.setText(
								Player2.getField().getMonstersArea().get(i)
										.getName());
				if (Player2.getField().getMonstersArea().get(i).getMode()
						.equals((Mode.ATTACK))) {
					gui.getPlayer2().getField().getMonstersArea().get(i)
							.setBackground(Color.RED);
				} else {
					gui.getPlayer2().getField().getMonstersArea().get(i)
							.setBackground(Color.GRAY);

				}
			}
		}
		gui.repaint();
		gui.validate();
	}

	public void updateSpellArea() {
		if (!Player1.getField().getSpellArea().isEmpty()) {
			for (int i = 0; i < Player1.getField().getSpellArea().size(); i++) {
				gui.getPlayer1()
						.getField()
						.getSpellArea()
						.get(i)
						.setText(
								Player1.getField().getSpellArea().get(i)
										.getName());
				gui.getPlayer1().getField().getSpellArea().get(i)
						.setBackground(Color.CYAN);
			}
		}
		if (!Player2.getField().getSpellArea().isEmpty()) {
			for (int i = 0; i < Player2.getField().getSpellArea().size(); i++) {
				gui.getPlayer2()
						.getField()
						.getSpellArea()
						.get(i)
						.setText(
								Player2.getField().getSpellArea().get(i)
										.getName());
				gui.getPlayer2().getField().getSpellArea().get(i)
						.setBackground(Color.CYAN);
			}
		}
		gui.validate();
	}

	public void updateTurns() {
		if (Player1 == board.getActivePlayer()) {
			gui.getPlayer1().getTools().getname().setForeground(Color.RED);
			;
			gui.getPlayer2().getTools().getname().setForeground(Color.BLACK);
		} else {
			gui.getPlayer2().getTools().getname().setForeground(Color.RED);
			gui.getPlayer1().getTools().getname().setForeground(Color.BLACK);
		}

		gui.repaint();
		gui.validate();
	}

	public void updateGraveyard() {
		if (Player1.getField().getGraveyard().size() > 0) {
			gui.getPlayer1()
					.getTools()
					.getGraveyard()
					.setText(
							""
									+ Player1
											.getField()
											.getGraveyard()
											.get(board.getActivePlayer()
													.getField().getGraveyard()
													.size() - 1).getName());
		}
		if (Player2.getField().getGraveyard().size() > 0) {
			gui.getPlayer2()
					.getTools()
					.getGraveyard()
					.setText(
							""
									+ Player2
											.getField()
											.getGraveyard()
											.get(board.getActivePlayer()
													.getField().getGraveyard()
													.size() - 1).getName());
		}
		gui.validate();
	}

	public void constructHand() {

		ArrayList<HandButtons> handGUI1 = gui.getPlayer1().getHand().getHand();
		ArrayList<HandButtons> handGUI2 = gui.getPlayer2().getHand().getHand();
		ArrayList<Card> handGame1 = Player1.getField().getHand();
		ArrayList<Card> handGame2 = Player2.getField().getHand();

		for (int i = 0; i < handGame1.size(); i++) {

			gui.getPlayer1().getHand().getHand().add(new HandButtons());
			gui.getPlayer1().getHand()
					.add(gui.getPlayer1().getHand().getHand().get(i));
			gui.getPlayer1().getHand().getHand().get(i).setVisible(true);

			gui.getPlayer1().getHand().getHand().get(i)
					.setText(handGame1.get(i).getName());
			gui.getPlayer1().getHand().getHand().get(i)
					.setBackground(Color.YELLOW);

		}
		for (int i = 0; i < handGame2.size(); i++) {

			gui.getPlayer2().getHand().getHand().add(new HandButtons());
			gui.getPlayer2().getHand()
					.add(gui.getPlayer2().getHand().getHand().get(i));
			gui.getPlayer2().getHand().getHand().get(i).setVisible(true);

			gui.getPlayer2().getHand().getHand().get(i)
					.setText(handGame2.get(i).getName());
			gui.getPlayer2().getHand().getHand().get(i)
					.setBackground(Color.YELLOW);

		}

		gui.repaint();
		gui.validate();
	}

	public void updateHand() {
		for (int i = 0; i < gui.getPlayer1().getHand().getHand().size(); i++) {
			gui.getPlayer1().getHand().getHand().get(i)
					.setText(Player1.getField().getHand().get(i).getName());
		}
		for (int i = 0; i < gui.getPlayer2().getHand().getHand().size(); i++) {
			gui.getPlayer2().getHand().getHand().get(i)
					.setText(Player2.getField().getHand().get(i).getName());
		}
	}

	/*
	 * public void addActionListenersToHand() { for(int i = 0;
	 * i<gui.getPlayer1().getHand().getHand().size();i++) {
	 * if(!gui.getPlayer1().
	 * getHand().getHand().get(i).getActionListeners().equals(this))
	 * gui.getPlayer1().getHand().getHand().get(i).addActionListener(this);
	 * 
	 * } for(int i = 0; i<gui.getPlayer2().getHand().getHand().size();i++) {
	 * 
	 * if(!gui.getPlayer2().getHand().getHand().get(i).getActionListeners().equals
	 * (this))
	 * gui.getPlayer2().getHand().getHand().get(i).addActionListener(this);
	 * 
	 * }
	 * 
	 * }/* /*public void updateHandLess() { int handGUI1 =
	 * gui.getPlayer1().getHand().getHand().size(); int handGUI2 =
	 * gui.getPlayer2().getHand().getHand().size(); int handGame1 =
	 * Player1.getField().getHand().size(); int handGame2 =
	 * Player2.getField().getHand().size(); int diff1; int diff2; if (handGame1
	 * < handGUI1) { diff1 = handGUI1 - handGame1; if (diff1 > 0) { for (int i =
	 * 0; i < gui.getPlayer1().getHand().getHand().size(); i++) { boolean found
	 * = false; for (int j = 0; i < handGame1; i++) { if (gui.getPlayer1()
	 * .getHand() .getHand() .get(i) .getText()
	 * .equals(Player1.getField().getHand().get(j) .getName())) found = true; }
	 * if (found == false) { gui.getPlayer1().getHand().getHand().remove(i); } }
	 * } }
	 * 
	 * if (handGame2 < handGUI2) { diff2 = handGUI2 - handGame2; if (diff2 > 0)
	 * { for (int i = 0; i < handGUI2; i++) { boolean found = false; for (int j
	 * = 0; i < handGame2; i++) { if (gui.getPlayer2() .getHand() .getHand()
	 * .get(i) .getText() .equals(Player2.getField().getHand().get(j)
	 * .getName())) found = true; } if (found == false) {
	 * gui.getPlayer2().getHand().getHand().remove(i); } } } } updateHand(); }
	 * 
	 * public void updateHandMore() { int handGUI1 =
	 * gui.getPlayer1().getHand().getHand().size(); int handGUI2 =
	 * gui.getPlayer2().getHand().getHand().size(); int handGame1 =
	 * Player1.getField().getHand().size(); int handGame2 =
	 * Player2.getField().getHand().size(); int diff1; int diff2;
	 * 
	 * if (handGUI1 < handGame1) { diff1 = handGame1 - handGUI1;
	 * 
	 * if (diff1 > 0) {
	 * 
	 * for (int i = 0; i < diff1; i++) { HandButtons extra = new HandButtons();
	 * gui.getPlayer1().getHand().getHand().add(extra); extra.setVisible(true);
	 * 
	 * 
	 * } } updateHand(); } if (handGUI2 < handGame2) { diff2 = handGame2 -
	 * handGUI2;
	 * 
	 * if (diff2 > 0) {
	 * 
	 * for(int i = 0; i<diff2; i++){ HandButtons extra = new HandButtons();
	 * gui.getPlayer2().getHand().getHand().add(extra); extra.setVisible(true);
	 * } } updateHand(); }
	 * 
	 * 
	 * }
	 */

	public void updateHandLess() {
		if (gui.getPlayer1().getHand().getHand().size() > Player1.getField()
				.getHand().size()) {
			for (int i = 0; i < gui.getPlayer1().getHand().getHand().size(); i++) {
				boolean found = true;
				for (int j = 0; j < Player1.getField().getHand().size(); j++) {
					if (!gui.getPlayer1()
							.getHand()
							.getHand()
							.get(i)
							.getText()
							.equals(Player1.getField().getHand().get(j)
									.getName())) {
						found = false;
					}
				}
				if (found = false) {
					gui.getPlayer1()
							.getHand()
							.getHand()
							.remove(gui.getPlayer1().getHand().getHand().get(i));
				}
			}
		}

		if (gui.getPlayer2().getHand().getHand().size() > Player2.getField()
				.getHand().size()) {
			for (int i = 0; i < gui.getPlayer2().getHand().getHand().size(); i++) {
				boolean found = true;
				for (int j = 0; j < Player2.getField().getHand().size(); j++) {
					if (!gui.getPlayer2()
							.getHand()
							.getHand()
							.get(i)
							.getText()
							.equals(Player2.getField().getHand().get(j)
									.getName())) {
						found = false;
					}
				}
				if (found = false) {
					gui.getPlayer2()
							.getHand()
							.getHand()
							.remove(gui.getPlayer2().getHand().getHand().get(i));
				}
			}
		}
	}

	public void Control() throws MissingFieldException,
			UnknownCardTypeException, UnknownSpellCardException,
			EmptyFieldException, IOException {

		updateHandLess();
		constructHand();

		updateLifePoints();
		updateGraveyard();
		updateTurns();
		updateSpellArea();
		updateMonstersArea();
		changeHands();
		updateDeck();
		updatePhase();
		showWinner();

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		try {
			if (click1 == null) {
				if (e.getSource() instanceof HandButtons) {
					click1 = (HandButtons) e.getSource();
					String s = click1.getText();
					System.out.println(s);

					showCards(s);

				} else {
					if (e.getSource() instanceof GraveyardButton) {
						click1 = (GraveyardButton) e.getSource();

						JOptionPane
								.showMessageDialog(
										null,
										board.getActivePlayer()
												.getField()
												.getGraveyard()
												.get(board.getActivePlayer()
														.getField()
														.getGraveyard().size() - 1)
												.getName()
												+ "\n"
												+ board.getActivePlayer()
														.getField()
														.getGraveyard()
														.get(board
																.getActivePlayer()
																.getField()
																.getGraveyard()
																.size() - 1)
														.getDescription());
						click1 = null;
					} else if (e.getSource() instanceof ActionButton) {
						click1 = (ActionButton) e.getSource();
						// System.out.println(click1.getText());

						doActionTurns(click1.getText());
						Control();
						click1 = null;

						// System.out.println(click1);

					} else if (e.getSource() instanceof DeckButton) {
						click1 = (DeckButton) e.getSource();
						JOptionPane
								.showMessageDialog(
										null,
										"You can't draw cards in the middle of the game except with certain spells or ending the turn");
						click1 = null;
					}

					else {
						if (e.getSource() instanceof MonsterButton) {
							click1 = (MonsterButton) e.getSource();
							String s = click1.getText();
							// System.out.println(s);
							showMonsters(s);
						} else {
							if (e.getSource() instanceof SpellButton) {
								click1 = (SpellButton) e.getSource();
								String s = click1.getText();
								// System.out.println(s);
								showSpells(s);
							}
						}
					}
				}
			}

			else {
				if (click2 == null) {
					if (e.getSource() instanceof ActionButton) {
						click2 = (ActionButton) e.getSource();
						// JOptionPane.showMessageDialog(null, "hi"); //(it
						// prints but doesn't show the optionpane)
						// System.out.println(click2.getText());
						if (click1 instanceof MonsterButton
								|| click1 instanceof HandButtons) {
							doAction(click2.getText(), click1.getText());
							doAction3(click2.getText(), click1.getText(), null);
						} else
							doActionTurns(click2.getText());
						Control();
						click2 = null;
						click1 = null;
						// MSH SHAGHALA AND I HAVE NO IDEA WHY!

					} else if (e.getSource() instanceof MonsterButton) {
						click2 = (MonsterButton) e.getSource();
						System.out.println(click2.getText());
						showMonsters(click2.getText());
					} else if (e.getSource() instanceof GraveyardButton) {
						click2 = (GraveyardButton) e.getSource();
						if (!board.getActivePlayer().getField().getGraveyard()
								.isEmpty())
							JOptionPane.showMessageDialog(
									null,
									board.getActivePlayer()
											.getField()
											.getGraveyard()
											.get(board.getActivePlayer()
													.getField().getGraveyard()
													.size() - 1).getName()
											+ "\n"
											+ board.getActivePlayer()
													.getField()
													.getGraveyard()
													.get(board
															.getActivePlayer()
															.getField()
															.getGraveyard()
															.size() - 1)
													.getDescription());
						click1 = null;
						click2 = null;
					} else if (e.getSource() instanceof SpellButton) {
						click2 = (SpellButton) e.getSource();
						showSpells(click2.getText());
					} else if (e.getSource() instanceof HandButtons)

					{
						
						click1 = (HandButtons) e.getSource();
						showCards(click2.getText());
					}
				} else {
					if (click1 != null && click2 != null && click3 == null) {
						if (e.getSource() instanceof MonsterButton) {
							click3 = (MonsterButton) e.getSource();
							if (click2 instanceof ActionButton
									&& click1 instanceof MonsterButton) {
								System.out.println("hi");
								doAction3(click2.getText(), click1.getText(),
										click3.getText());
								Control();
							} else {

								showMonsters(click3.getText());
							}
							click1 = null;
							click2 = null;
							click3 = null;
							// System.out.println(click3.getText());

							// doAction3(click2.getText(), click1.getText(),
							// click3.getText());

							// 3ayza azbotha

						} else if (e.getSource() instanceof GraveyardButton) {
							click3 = (GraveyardButton) e.getSource();
							if (!board.getActivePlayer().getField()
									.getGraveyard().isEmpty())
								JOptionPane
										.showMessageDialog(
												null,
												board.getActivePlayer()
														.getField()
														.getGraveyard()
														.get(board
																.getActivePlayer()
																.getField()
																.getGraveyard()
																.size() - 1)
														.getName()
														+ "\n"
														+ board.getActivePlayer()
																.getField()
																.getGraveyard()
																.get(board
																		.getActivePlayer()
																		.getField()
																		.getGraveyard()
																		.size() - 1)
																.getDescription());
							click1 = null;
							click2 = null;
							click3 = null;
						} else if (e.getSource() instanceof HandButtons) {
							click3 = (HandButtons) e.getSource();
							showCards(click3.getText());

						} else if (e.getSource() instanceof SpellButton) {
							click3 = (SpellButton) e.getSource();
							showSpells(click3.getText());
						} else if (click3 instanceof ActionButton) {
							click3 = (ActionButton) e.getSource();
							doActionTurns(click3.getText());
							doAction(click3.getText(), click2.getText());
							Control();
							click1 = null;
							click2 = null;
							click3 = null;
						}

					} else {
						if (click4 == null && click1 != null && click2 != null
								&& click3 != null) {
							if (e.getSource() instanceof MonsterButton) {
								click4 = (MonsterButton) e.getSource();
								System.out.println("hehehe");
								if (click3 instanceof ActionButton
										&& click2 instanceof MonsterButton
										&& click1 instanceof ActionButton) {
									doAction3(click3.getText(),
											click2.getText(), click4.getText());
									Control();
								}
							}
						}
					}
				}
			}

		} catch (WrongPhaseException ex) {
			JOptionPane.showMessageDialog(null,
					"The action you are doing should not be done in "
							+ board.getActivePlayer().getField().getPhase());
			click1 = null;
			click2 = null;
			click3 = null;
		} catch (MultipleMonsterAdditionException ex) {
			JOptionPane.showMessageDialog(null,
					"You cannot add more than on monster per turn");
			click1 = null;
			click2 = null;
			click3 = null;
		} catch (NoMonsterSpaceException ex) {
			JOptionPane.showMessageDialog(null, "Your monster area is full");
			click1 = null;
			click2 = null;
			click3 = null;
		} catch (NoSpellSpaceException ex) {
			JOptionPane.showMessageDialog(null, "Your spell area is full");
			click1 = null;
			click2 = null;
			click3 = null;
		} catch (DefenseMonsterAttackException ex) {
			JOptionPane.showMessageDialog(null,
					"You cannot attack with a monster in Defense mode");
			click1 = null;
			click2 = null;
			click3 = null;
		} catch (MonsterMultipleAttackException ex) {
			JOptionPane.showMessageDialog(null,
					"You cannot attack more than once per turn");
			click1 = null;
			click2 = null;
			click3 = null;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "bekh");
			click1 = null;
			click2 = null;
			click3 = null;
		}

	}

	public void doAction3(String action, String card1, String card2)
			throws MissingFieldException, UnknownCardTypeException,
			UnknownSpellCardException, EmptyFieldException, IOException {
		switch (action) {
		case ("ATTACK"):
			if (Player1 == board.getActivePlayer()
					&& !(board.getOpponentPlayer().getField().getMonstersArea()
							.isEmpty())) {
				System.out.println("bekh");
				System.out.println(Player1.declareAttack(
						getMonsterActiveField(card1),
						getMonsterOpponentField(card2)));
				Control();
			} else if (Player2 == board.getActivePlayer()
					&& !(board.getOpponentPlayer().getField().getMonstersArea()
							.isEmpty())) {
				System.out.println("bekh");
				System.out.println(Player2.declareAttack(
						getMonsterActiveField(card1),
						getMonsterOpponentField(card2)));
				Control();
			}
			break;
		case ("Activate Spell"):
			if (Player1 == board.getActivePlayer()) {

				System.out.println("got there");
				switch (card1) {
				case ("Monster Reborn"):
					Player1.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Raigeki"):
					Player1.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Graceful Dice"):
					Player1.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Harpie's Feather Duster"):
					Player1.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Pot of Greed"):
					System.out.println(Player1.activateSpell(
							getSpellActiveField(card1), null));

					break;
				case ("Mage Power"):
					Player1.activateSpell(getSpellActiveField(card1),
							getMonsterOpponentField(card2));

					break;
				case ("Heavy Storm"):
					Player1.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Dark Hole"):
					Player1.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Change of Heart"):
					Player1.activateSpell(getSpellActiveField(card1),
							getMonsterOpponentField(card2));

					break;
				case ("Card Destruction"):
					System.out.println(Player1.activateSpell(
							getSpellActiveField(card1), null));
					break;

				}
				Control();
			} else {
				switch (card1) {
				case ("Monster Reborn"):
					Player2.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Raigeki"):
					Player2.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Graceful Dice"):
					Player2.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Harpie's Feather Duster"):
					Player2.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Pot of Greed"):
					Player2.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Mage Power"):
					Player2.activateSpell(getSpellActiveField(card1),
							getMonsterOpponentField(card2));

					break;
				case ("Heavy Storm"):
					Player2.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Dark Hole"):
					Player2.activateSpell(getSpellActiveField(card1), null);

					break;
				case ("Change of Heart"):
					Player2.activateSpell(getSpellActiveField(card1),
							getMonsterOpponentField(card2));

					break;
				case ("Card Destruction"):
					Player2.activateSpell(getSpellActiveField(card1), null);
					break;

				}
				Control();
			}
			break;
		}

	}

	public void doActionTurns(String s) throws Exception {
		switch (s) {
		case ("End Turn"):

			board.getActivePlayer().endTurn();

			Control();
			break;
		case ("End Phase"):

			board.getActivePlayer().endPhase();

			Control();
			break;
		}
	}

	public MonsterCard IdentifyStringMonster(String s) {

		switch (s) {
		case ("Blue-Eyes White Dragon"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);

			break;
		case ("Cosmo Queen"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Dark Magician"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Fence Guard"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Red-Eyes Black Dragon"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;

		case ("Gaia The Fierce Knight"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Summoned Skull"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);

			break;
		case ("Fence Guard Magician"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Dark Magician Girl"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);

			break;
		case ("Curse Of Dragon"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);

			break;
		case ("Fence Guard Dragon"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;

		case ("Alexandrite Dragon"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);

			break;
		case ("Vorse Raider"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Gemini Elf"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Fence Guard Apprentice"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Beta The Magnet Warrior"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Alligator Sword"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Gamma The Magnet Warrior"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Celtic Guardian"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Alpha The Magnet Warrior"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Harpie Lady"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Big Shield Gardna"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Witty Phantom"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Baby Dragon"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Cyber Jar"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);

			break;
		case ("Clown Zombie"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Time Wizard"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Man-Eater Bug"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);

			break;
		case ("Kuriboh"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		case ("Mokey Mokey"):

			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getMonsterActiveHand(s);
			else if (click1 instanceof MonsterButton
					&& click1.getText().equals(s))
				return getMonsterActiveField(s);
			else if (click3 instanceof MonsterButton
					&& click3.getText().equals(s))
				return getMonsterOpponentField(s);
			break;
		}
		return null;
	}

	public SpellCard IdentifyStringSpell(String s) {
		switch (s) {
		case ("Monster Reborn"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Raigeki"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Graceful Dice"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Harpie's Feather Duster"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Pot of Greed"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Mage Power"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;

		case ("Heavy Storm"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Dark Hole"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Change of Heart"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		case ("Card Destruction"):
			if (click1 instanceof HandButtons && click1.getText().equals(s))
				return getSpellActiveHand(s);
			else if (click1 instanceof SpellButton
					&& click1.getText().equals(s))
				return getSpellActiveField(s);

			break;
		}
		return null;
	}

	public void doAction(String action, String card) throws Exception {

		switch (action) {
		case ("Set Monster"):

			board.getActivePlayer().setMonster(getMonsterActiveHand(card));
			Control();

			break;
		case ("Summon Monster"):

			board.getActivePlayer().summonMonster(getMonsterActiveHand(card));
			Control();

			break;
		case ("Switch Monster"):

			board.getActivePlayer().switchMonsterMode(
					getMonsterActiveField(card));
			Control();

			break;
		case ("Set Spell"):

			board.getActivePlayer().setSpell(getSpellActiveHand(card));
			Control();

			break;
		case ("ATTACK"):
			board.getActivePlayer().declareAttack(getMonsterActiveField(card));
			Control();
			break;
		}

		// /Not completed! } }
	}

	public void showCards(String s) {
		JFrame imageWindow = new JFrame();

		JLabel pic = new JLabel();
		imageWindow.setSize(1000, 700);
		imageWindow.setLayout(new FlowLayout());
		imageWindow.setDefaultCloseOperation(imageWindow.HIDE_ON_CLOSE);
		switchMonster.setBackground(Color.orange);
		declareAttack.setBackground(Color.orange);
		summonMonster.setBackground(Color.orange);
		setMonster.setBackground(Color.orange);
		activateSpell.setBackground(Color.orange);
		setSpell.setBackground(Color.orange);
		JLabel Name;
		JLabel Description;
		JLabel Level;
		JLabel Attack;
		JLabel Defense;

		switch (s) {
		case ("Blue-Eyes White Dragon"):

			ImageIcon ImageIcon = new ImageIcon("Blue Eyes White Dragon.jpg");

			pic.setIcon(ImageIcon);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Cosmo Queen"):

			ImageIcon ImageIcon1 = new ImageIcon("Cosmo Queen.jpg");
			pic.setIcon(ImageIcon1);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Dark Magician"):
			ImageIcon ImageIcon2 = new ImageIcon("Dark Magician" + ".jpg");
			pic.setIcon(ImageIcon2);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;

		case ("Fence Guard"):
			ImageIcon ImageIcon3 = new ImageIcon("Fence Guard.jpg");
			pic.setIcon(ImageIcon3);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Red-Eyes Black Dragon"):
			ImageIcon ImageIcon4 = new ImageIcon("Red-Eyes Black Dragon.jpg");
			pic.setIcon(ImageIcon4);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;

		case ("Gaia The Fierce Knight"):
			ImageIcon ImageIcon5 = new ImageIcon("Gaia The Fierce Knight.jpg");
			pic.setIcon(ImageIcon5);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Summoned Skull"):
			ImageIcon ImageIcon6 = new ImageIcon("Summoned Skull.jpg");
			pic.setIcon(ImageIcon6);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Fence Guard Magician"):
			ImageIcon ImageIcon7 = new ImageIcon("Fence Guard Magician"
					+ ".jpg");
			pic.setIcon(ImageIcon7);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Dark Magician Girl"):
			ImageIcon ImageIcon8 = new ImageIcon("Dark Magician Girl" + ".jpg");
			pic.setIcon(ImageIcon8);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Curse Of Dragon"):
			ImageIcon ImageIcon9 = new ImageIcon("Curse Of Dragon.jpg");
			pic.setIcon(ImageIcon9);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Fence Guard Dragon"):
			ImageIcon ImageIcon10 = new ImageIcon("Fence Guard Dragon.jpg");
			pic.setIcon(ImageIcon10);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;

		case ("Alexandrite Dragon"):
			ImageIcon ImageIcon11 = new ImageIcon("Alexandrite Dragon.jpg");
			pic.setIcon(ImageIcon11);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Vorse Raider"):
			ImageIcon ImageIcon12 = new ImageIcon("Vorse Raider.jpg");
			pic.setIcon(ImageIcon12);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Gemini Elf"):
			ImageIcon ImageIcon13 = new ImageIcon("Gemini Elf.jpg");
			pic.setIcon(ImageIcon13);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Fence Guard Apprentice"):
			ImageIcon ImageIcon14 = new ImageIcon("Fence Guard Apprentice"
					+ ".jpg");
			pic.setIcon(ImageIcon14);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Beta The Magnet Warrior"):
			ImageIcon ImageIcon15 = new ImageIcon("Beta The Magnet Warrior.jpg");
			pic.setIcon(ImageIcon15);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Alligator Sword"):
			ImageIcon ImageIcon16 = new ImageIcon("Alligator Sword" + ".jpg");
			pic.setIcon(ImageIcon16);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Gamma The Magnet Warrior"):
			ImageIcon ImageIcon17 = new ImageIcon(
					"Gamma The Magnet Warrior.jpg");
			pic.setIcon(ImageIcon17);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Celtic Guardian"):
			ImageIcon ImageIcon18 = new ImageIcon("Celtic Guardian.jpg");
			pic.setIcon(ImageIcon18);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Alpha The Magnet Warrior"):

			ImageIcon ImageIcon19 = new ImageIcon("Alpha The Magnet Warrior"
					+ ".jpg");
			pic.setIcon(ImageIcon19);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Harpie Lady"):
			ImageIcon ImageIcon20 = new ImageIcon("Harpie Lady" + ".jpg");
			pic.setIcon(ImageIcon20);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Big Shield Gardna"):
			ImageIcon ImageIcon21 = new ImageIcon("Big Shield Gardna.jpg");
			pic.setIcon(ImageIcon21);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Witty Phantom"):
			ImageIcon ImageIcon22 = new ImageIcon("Witty Phantom.jpg");
			pic.setIcon(ImageIcon22);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Baby Dragon"):
			ImageIcon ImageIcon23 = new ImageIcon("Baby Dragon" + ".jpg");
			pic.setIcon(ImageIcon23);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Cyber Jar"):
			ImageIcon ImageIcon24 = new ImageIcon("Cyber Jar" + ".jpg");
			pic.setIcon(ImageIcon24);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Clown Zombie"):
			ImageIcon ImageIcon25 = new ImageIcon("Clown Zombie" + ".jpg");
			pic.setIcon(ImageIcon25);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Time Wizard"):
			ImageIcon ImageIcon26 = new ImageIcon("Time Wizard" + ".jpg");
			pic.setIcon(ImageIcon26);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Man-Eater Bug"):
			ImageIcon ImageIcon27 = new ImageIcon("Man-Eater Bug" + ".jpg");
			pic.setIcon(ImageIcon27);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Kuriboh"):
			ImageIcon ImageIcon28 = new ImageIcon("Kuriboh.jpg");
			pic.setIcon(ImageIcon28);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Mokey Mokey"):
			ImageIcon ImageIcon29 = new ImageIcon("Mokey Mokey" + ".jpg");
			pic.setIcon(ImageIcon29);
			imageWindow.add(pic);
			Name = new JLabel("Name: "+ s);
			Description = new JLabel("Description: " +getMonsterActiveHand(s).getDescription());
			Level = new JLabel("Level: " + getMonsterActiveHand(s).getLevel());
			Attack = new JLabel("Attack Points: "+getMonsterActiveHand(s).getAttackPoints());
			Defense = new JLabel("Defense Points: "+getMonsterActiveHand(s).getDefensePoints());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(Level);
			imageWindow.add(Attack);
			imageWindow.add(Defense);
			imageWindow.add(setMonster);
			imageWindow.add(summonMonster);
			// imageWindow.add(declareAttack);
			// imageWindow.add(switchMonster);
			imageWindow.setVisible(true);
			
			imageWindow.validate();
			break;
		case ("Monster Reborn"):
			ImageIcon ImageIcon30 = new ImageIcon("Monster Reborn.jpg");
			pic.setIcon(ImageIcon30);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Raigeki"):
			ImageIcon ImageIcon31 = new ImageIcon("Raigeki.jpg");
			pic.setIcon(ImageIcon31);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Graceful Dice"):
			ImageIcon ImageIcon32 = new ImageIcon("Gracefull Dice.jpg");
			pic.setIcon(ImageIcon32);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Harpie's Feather Duster"):
			ImageIcon ImageIcon33 = new ImageIcon("Harpies Feather Duster.jpg");
			pic.setIcon(ImageIcon33);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Pot of Greed"):
			ImageIcon ImageIcon34 = new ImageIcon("Pot Of Greed.jpg");
			pic.setIcon(ImageIcon34);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Mage Power"):
			ImageIcon ImageIcon35 = new ImageIcon("Mage Power.jpg");
			pic.setIcon(ImageIcon35);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Heavy Storm"):
			ImageIcon ImageIcon36 = new ImageIcon("Heavy Storm.jpg");
			pic.setIcon(ImageIcon36);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Dark Hole"):
			ImageIcon ImageIcon37 = new ImageIcon("Dark Hole.jpg");
			pic.setIcon(ImageIcon37);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Change Of Heart"):
			ImageIcon ImageIcon38 = new ImageIcon("Change of Heart.jpg");
			pic.setIcon(ImageIcon38);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;
		case ("Card Destruction"):
			ImageIcon ImageIcon39 = new ImageIcon("Card Destruction.jpg");
			pic.setIcon(ImageIcon39);
			imageWindow.add(pic);
			Name = new JLabel("Name: " + s);
			Description = new JLabel("Description: " + getSpellActiveHand(s).getDescription());
			imageWindow.add(Name);
			imageWindow.add(Description);
			imageWindow.add(setSpell);
			imageWindow.add(activateSpell);
			imageWindow.setVisible(true);
			imageWindow.validate();
			break;

		}

	}

	public void showMonsters(String s) {
		JFrame imageWindow = new JFrame();

		JLabel pic = new JLabel();
		imageWindow.setSize(1000, 700);
		imageWindow.setDefaultCloseOperation(imageWindow.HIDE_ON_CLOSE);
		imageWindow.setLayout(new FlowLayout());
		declareAttack.setBackground(Color.blue);
		switchMonster.setBackground(Color.BLUE);
		JLabel Name;
		JLabel Description;
		JLabel Level;
		JLabel Attack;
		JLabel Defense;
		JLabel Modes;
		if (board.getActivePlayer().getField().getMonstersArea()
				.contains(getMonsterActiveField(s))
				|| (board.getOpponentPlayer().getField().getMonstersArea()
						.contains(getMonsterOpponentField(s)) && getMonsterOpponentField(
						s).getMode() == Mode.ATTACK )) {
			// if (click1 instanceof MonsterButton) {
			switch (s) {

			case ("Blue-Eyes White Dragon"):

				ImageIcon ImageIcon = new ImageIcon(
						"Blue Eyes White Dragon.jpg");

				pic.setIcon(ImageIcon);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Cosmo Queen"):

				ImageIcon ImageIcon1 = new ImageIcon("Cosmo Queen.jpg");
				pic.setIcon(ImageIcon1);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Dark Magician"):
				ImageIcon ImageIcon2 = new ImageIcon("Dark Magician" + ".jpg");
				pic.setIcon(ImageIcon2);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Fence Guard"):
				ImageIcon ImageIcon3 = new ImageIcon("Fence Guard.jpg");
				pic.setIcon(ImageIcon3);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Red-Eyes Black Dragon"):
				ImageIcon ImageIcon4 = new ImageIcon(
						"Red-Eyes Black Dragon.jpg");
				pic.setIcon(ImageIcon4);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;

			case ("Gaia The Fierce Knight"):
				ImageIcon ImageIcon5 = new ImageIcon(
						"Gaia The Fierce Knight.jpg");
				pic.setIcon(ImageIcon5);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Summoned Skull"):
				ImageIcon ImageIcon6 = new ImageIcon("Summoned Skull.jpg");
				pic.setIcon(ImageIcon6);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Fence Guard Magician"):
				ImageIcon ImageIcon7 = new ImageIcon("Fence Guard Magician"
						+ ".jpg");
				pic.setIcon(ImageIcon7);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Dark Magician Girl"):
				ImageIcon ImageIcon8 = new ImageIcon("Dark Magician Girl"
						+ ".jpg");
				pic.setIcon(ImageIcon8);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Curse Of Dragon"):
				ImageIcon ImageIcon9 = new ImageIcon("Curse Of Dragon.jpg");
				pic.setIcon(ImageIcon9);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Fence Guard Dragon"):
				ImageIcon ImageIcon10 = new ImageIcon("Fence Guard Dragon.jpg");
				pic.setIcon(ImageIcon10);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;

			case ("Alexandrite Dragon"):
				ImageIcon ImageIcon11 = new ImageIcon("Alexandrite Dragon.jpg");
				pic.setIcon(ImageIcon11);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Vorse Raider"):
				ImageIcon ImageIcon12 = new ImageIcon("Vorse Raider.jpg");
				pic.setIcon(ImageIcon12);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Gemini Elf"):
				ImageIcon ImageIcon13 = new ImageIcon("Gemini Elf.jpg");
				pic.setIcon(ImageIcon13);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Fence Guard Apprentice"):
				ImageIcon ImageIcon14 = new ImageIcon("Fence Guard Apprentice"
						+ ".jpg");
				pic.setIcon(ImageIcon14);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Beta The Magnet Warrior"):
				ImageIcon ImageIcon15 = new ImageIcon(
						"Beta The Magnet Warrior.jpg");
				pic.setIcon(ImageIcon15);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Alligator Sword"):
				ImageIcon ImageIcon16 = new ImageIcon("Alligator Sword"
						+ ".jpg");
				pic.setIcon(ImageIcon16);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Gamma The Magnet Warrior"):
				ImageIcon ImageIcon17 = new ImageIcon(
						"Gamma The Magnet Warrior.jpg");
				pic.setIcon(ImageIcon17);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Celtic Guardian"):
				ImageIcon ImageIcon18 = new ImageIcon("Celtic Guardian.jpg");
				pic.setIcon(ImageIcon18);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Alpha The Magnet Warrior"):

				ImageIcon ImageIcon19 = new ImageIcon(
						"Alpha The Magnet Warrior" + ".jpg");
				pic.setIcon(ImageIcon19);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Harpie Lady"):
				ImageIcon ImageIcon20 = new ImageIcon("Harpie Lady" + ".jpg");
				pic.setIcon(ImageIcon20);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Big Shield Gardna"):
				ImageIcon ImageIcon21 = new ImageIcon("Big Shield Gardna.jpg");
				pic.setIcon(ImageIcon21);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Witty Phantom"):
				ImageIcon ImageIcon22 = new ImageIcon("Witty Phantom.jpg");
				pic.setIcon(ImageIcon22);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Baby Dragon"):
				ImageIcon ImageIcon23 = new ImageIcon("Baby Dragon" + ".jpg");
				pic.setIcon(ImageIcon23);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Cyber Jar"):
				ImageIcon ImageIcon24 = new ImageIcon("Cyber Jar" + ".jpg");
				pic.setIcon(ImageIcon24);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Clown Zombie"):
				ImageIcon ImageIcon25 = new ImageIcon("Clown Zombie" + ".jpg");
				pic.setIcon(ImageIcon25);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Time Wizard"):
				ImageIcon ImageIcon26 = new ImageIcon("Time Wizard" + ".jpg");
				pic.setIcon(ImageIcon26);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Man-Eater Bug"):
				ImageIcon ImageIcon27 = new ImageIcon("Man-Eater Bug" + ".jpg");
				pic.setIcon(ImageIcon27);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Kuriboh"):
				ImageIcon ImageIcon28 = new ImageIcon("Kuriboh.jpg");
				pic.setIcon(ImageIcon28);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;
			case ("Mokey Mokey"):
				ImageIcon ImageIcon29 = new ImageIcon("Mokey Mokey" + ".jpg");
				pic.setIcon(ImageIcon29);
				imageWindow.add(pic);
				Name = new JLabel("Name: "+ s);
				Description = new JLabel("Description: " +getMonsterActiveField(s).getDescription());
				Level = new JLabel("Level: " + getMonsterActiveField(s).getLevel());
				Attack = new JLabel("Attack Points: "+getMonsterActiveField(s).getAttackPoints());
				Defense = new JLabel("Defense Points: "+getMonsterActiveField(s).getDefensePoints());
				Modes = new JLabel("Mode: " + getMonsterActiveField(s).getMode());
				imageWindow.add(Name);
				imageWindow.add(Description);
				imageWindow.add(Level);
				imageWindow.add(Attack);
				imageWindow.add(Defense);
				imageWindow.add(Modes);
				// imageWindow.add(setMonster);
				// imageWindow.add(summonMonster);
				imageWindow.add(declareAttack);
				imageWindow.add(switchMonster);
				imageWindow.setVisible(true);
				imageWindow.validate();

				break;

			}
		}
		else JOptionPane.showMessageDialog(null, "You can't access the monster's information while it's in defense mode");
	}

	public void showSpells(String s) {
		JFrame imageWindow = new JFrame();

		JLabel pic = new JLabel();
		imageWindow.setSize(1000, 700);
		imageWindow.setDefaultCloseOperation(imageWindow.HIDE_ON_CLOSE);
		imageWindow.setLayout(new FlowLayout());
		activateSpell.setBackground(Color.GREEN);
		JLabel Name;
		JLabel Description;
		if (board.getActivePlayer().getField().getSpellArea()
				.contains(getSpellActiveField(s))) {
			switch (s) {
			case ("Monster Reborn"):
				ImageIcon ImageIcon30 = new ImageIcon("Monster Reborn.jpg");
				pic.setIcon(ImageIcon30);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Raigeki"):
				ImageIcon ImageIcon31 = new ImageIcon("Raigeki.jpg");
				pic.setIcon(ImageIcon31);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Graceful Dice"):
				ImageIcon ImageIcon32 = new ImageIcon("Gracefull Dice.jpg");
				pic.setIcon(ImageIcon32);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Harpie's Feather Duster"):
				ImageIcon ImageIcon33 = new ImageIcon(
						"Harpies Feather Duster.jpg");
				pic.setIcon(ImageIcon33);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Pot of Greed"):
				ImageIcon ImageIcon34 = new ImageIcon("Pot Of Greed.jpg");
				pic.setIcon(ImageIcon34);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Mage Power"):
				ImageIcon ImageIcon35 = new ImageIcon("Mage Power.jpg");
				pic.setIcon(ImageIcon35);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Heavy Storm"):
				ImageIcon ImageIcon36 = new ImageIcon("Heavy Storm.jpg");
				pic.setIcon(ImageIcon36);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Dark Hole"):
				ImageIcon ImageIcon37 = new ImageIcon("Dark Hole.jpg");
				pic.setIcon(ImageIcon37);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Change of Heart"):
				ImageIcon ImageIcon38 = new ImageIcon("Change of Heart.jpg");
				pic.setIcon(ImageIcon38);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			case ("Card Destruction"):
				ImageIcon ImageIcon39 = new ImageIcon("Card Destruction.jpg");
				pic.setIcon(ImageIcon39);
				imageWindow.add(pic);
				Name = new JLabel("Name: " + getSpellActiveField(s).getName());
				Description = new JLabel("Description: " + getSpellActiveField(s).getDescription());
				imageWindow.add(Name);
				imageWindow.add(Description);
				// imageWindow.add(setSpell);
				imageWindow.add(activateSpell);
				imageWindow.setVisible(true);
				imageWindow.validate();
				break;
			}
		}
		else JOptionPane.showMessageDialog(null, "The Spell is hidden");
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board b) {
		board = b;
	}

	public Player getPlayer1() {
		return Player1;
	}

	public void setPlayer1(Player player1) {
		Player1 = player1;
	}

	public Player getPlayer2() {
		return Player2;
	}

	public void setPlayer2(Player player2) {
		Player2 = player2;
	}

	public MonsterCard getMonsterActiveField(String s) {
		if (Player1 == board.getActivePlayer()) {
			for (int i = 0; i < Player1.getField().getMonstersArea().size(); i++) {
				if (s.equals(Player1.getField().getMonstersArea().get(i)
						.getName())) {
					Player1.getField().getMonstersArea().get(i).getName();
					return Player1.getField().getMonstersArea().get(i);
				}
			}
		} else {
			for (int i = 0; i < Player2.getField().getMonstersArea().size(); i++) {
				if (s.equals(Player2.getField().getMonstersArea().get(i)
						.getName())) {
					Player2.getField().getMonstersArea().get(i).getName();
					return Player2.getField().getMonstersArea().get(i);
				}
			}
		}
		return null;
	}

	public SpellCard getSpellActiveField(String s) {

		if (Player1 == board.getActivePlayer()) {
			for (int i = 0; i < Player1.getField().getSpellArea().size(); i++) {
				if (s.equals(Player1.getField().getSpellArea().get(i).getName())) {
					Player1.getField().getSpellArea().get(i).getName();
					return Player1.getField().getSpellArea().get(i);
				}
			}
		} else {
			for (int i = 0; i < Player2.getField().getSpellArea().size(); i++) {
				if (s.equals(Player2.getField().getSpellArea().get(i).getName())) {
					Player2.getField().getSpellArea().get(i).getName();
					return Player2.getField().getSpellArea().get(i);
				}
			}
		}
		return null;
	}

	public MonsterCard getMonsterOpponentField(String s) {

		if (Player1 == board.getOpponentPlayer()) {
			for (int i = 0; i < Player1.getField().getMonstersArea().size(); i++) {
				if (s.equals(Player1.getField().getMonstersArea().get(i)
						.getName()))
					return Player1.getField().getMonstersArea().get(i);
			}
		} else {
			for (int i = 0; i < Player2.getField().getMonstersArea().size(); i++) {
				if (s.equals(Player2.getField().getMonstersArea().get(i)
						.getName()))
					return Player2.getField().getMonstersArea().get(i);
			}
		}
		return null;
	}

	public SpellCard getSpellOpponentField(String s) {

		if (Player1 == board.getOpponentPlayer()) {
			for (int i = 0; i < Player1.getField().getSpellArea().size(); i++) {
				if (s.equals(Player1.getField().getSpellArea().get(i).getName()))
					return Player1.getField().getSpellArea().get(i);
			}
		} else {
			for (int i = 0; i < Player2.getField().getSpellArea().size(); i++) {
				if (s.equals(Player2.getField().getSpellArea().get(i).getName()))
					return Player2.getField().getSpellArea().get(i);
			}
		}
		return null;
	}

	public MonsterCard getMonsterActiveHand(String s) {

		if (Player1 == board.getActivePlayer()) {
			for (int i = 0; i < Player1.getField().getHand().size(); i++) {
				if (s.equals(Player1.getField().getHand().get(i).getName())) {
					System.out.println(Player1.getField().getHand().get(i)
							.getName());
					return (MonsterCard) Player1.getField().getHand().get(i);

				}
			}
		} else {
			for (int i = 0; i < Player2.getField().getHand().size(); i++) {
				if (s.equals(Player2.getField().getHand().get(i).getName())) {
					System.out.println(Player2.getField().getHand().get(i)
							.getName());
					return (MonsterCard) Player2.getField().getHand().get(i);

				}
			}
		}
		return null;
	}

	public SpellCard getSpellActiveHand(String s) {

		if (Player1 == board.getActivePlayer()) {
			for (int i = 0; i < Player1.getField().getHand().size(); i++) {
				if (s.equals(Player1.getField().getHand().get(i).getName())) {
					return (SpellCard) Player1.getField().getHand().get(i);

				}
			}
		} else {
			for (int i = 0; i < Player2.getField().getHand().size(); i++) {
				if (s.equals(Player2.getField().getHand().get(i).getName())) {
					return (SpellCard) Player2.getField().getHand().get(i);

				}
			}
		}
		return null;
	}

	public MonsterCard getMonsterOpponentHand(String s) {

		if (Player1 == board.getOpponentPlayer()) {
			for (int i = 0; i < Player1.getField().getHand().size(); i++) {
				if (s.equals(Player1.getField().getHand().get(i).getName())) {
					return (MonsterCard) Player1.getField().getHand().get(i);

				}
			}
		} else {
			for (int i = 0; i < Player2.getField().getHand().size(); i++) {
				if (s.equals(Player2.getField().getHand().get(i).getName())) {
					return (MonsterCard) Player2.getField().getHand().get(i);

				}
			}
		}
		return null;
	}

	public SpellCard getSpellOpponentHand(String s) {

		if (Player1 == board.getOpponentPlayer()) {
			for (int i = 0; i < Player1.getField().getHand().size(); i++) {
				if (s.equals(Player1.getField().getHand().get(i).getName())) {
					return (SpellCard) Player1.getField().getHand().get(i);

				}
			}
		} else {
			for (int i = 0; i < Player2.getField().getHand().size(); i++) {
				if (s.equals(Player2.getField().getHand().get(i).getName())) {
					return (SpellCard) Player2.getField().getHand().get(i);

				}
			}
		}
		return null;
	}

}
