package eg.edu.guc.yugioh.board.player;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist {

	private String name;
	private int lifePoints;
	private Field field;
	private Boolean sameTurn = false;
	private Boolean gameOver = false;

	public Boolean getSameTurn() {
		return sameTurn;
	}

	public void setSameTurn(Boolean sameTurn) {
		this.sameTurn = sameTurn;
	}

	public Player(String name) throws MissingFieldException,
	UnknownCardTypeException, UnknownSpellCardException,
	EmptyFieldException, IOException {
		this.name = name;
		this.lifePoints = 8000;
		this.field = new Field();

	}

	public String getName() {
		return this.name;
	}

	public int getLifePoints() {
		return this.lifePoints;
	}

	public Field getField() {
		return this.field;
	}

	public int setLifePoints(int Points) {
		return this.lifePoints = Points;
	}

	public boolean summonMonster(MonsterCard monster)
			throws WrongPhaseException, NoMonsterSpaceException, MultipleMonsterAdditionException {
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if (this.field.getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();
		if(monster.getPlayed()==true||this.sameTurn==true) throw new MultipleMonsterAdditionException();
		if ((this.gameOver == false) && (Card.getBoard().getWinner() == null)
				&& (this.getField().getHand().contains(monster))
				&& (this.field.getMonstersArea().size() < 5)

				&& (Card.getBoard().getActivePlayer() == this)) {

			if ((monster.getPlayed() == false) && (this.getSameTurn() == false)) {
				// this.field.addMonsterToField(monster,Mode.ATTACK,false);
				if ((monster.getLevel() >= 1 && monster.getLevel() <= 4))
					this.field.addMonsterToField(monster, Mode.ATTACK, null);
				monster.setPlayed(true);
				this.setSameTurn(true);
			}

			if (this.field.getMonstersArea().contains(monster))
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean summonMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) throws WrongPhaseException,
			NoMonsterSpaceException ,MultipleMonsterAdditionException{
		// TODO Auto-generated method stub
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if (this.field.getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();
		if(monster.getPlayed()==true||this.sameTurn==true) throw new MultipleMonsterAdditionException();

		if ((this.gameOver == false) && (Card.getBoard().getWinner() == null)
				&& (this.getField().getHand().contains(monster))

				&& (Card.getBoard().getActivePlayer() == this)) {

			// if((monster.getLevel()>=1 && monster.getLevel()<=4))
			// this.field.addMonsterToField(monster, Mode.ATTACK, null);

			if ((this.getSameTurn() == false) && (monster.getPlayed() == false)
					&& (monster.getLevel() == 5 || monster.getLevel() == 6)
					&& sacrifices.size() == 1) {
				this.field.addMonsterToField(monster, Mode.ATTACK, sacrifices);
				monster.setPlayed(true);
				this.setSameTurn(true);
			}
			if ((this.getSameTurn() == false) && (monster.getPlayed() == false)
					&& (monster.getLevel() >=1 && monster.getLevel() <= 4)
					&& sacrifices==null) {
				this.field.addMonsterToField(monster, Mode.ATTACK, null);
				monster.setPlayed(true);
				this.setSameTurn(true);
			}

			if ((this.getSameTurn() == false) && (monster.getPlayed() == false)
					&& (monster.getLevel() == 7 || monster.getLevel() == 8)
					&& sacrifices.size() == 2) {
				this.field.addMonsterToField(monster, Mode.ATTACK, sacrifices);
				monster.setPlayed(true);
				this.setSameTurn(true);
			}

			if (this.field.getMonstersArea().contains(monster))
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster) throws WrongPhaseException,
			NoMonsterSpaceException, MultipleMonsterAdditionException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if (this.field.getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();
		if(monster.getPlayed()==true||this.sameTurn==true) throw new MultipleMonsterAdditionException();

		if ((this.gameOver == false) && (Card.getBoard().getWinner() == null)
				&& (this.getSameTurn() == false)
				&& (this.getField().getHand().contains(monster))
				&& (monster.getPlayed() == false)
				&& (monster.getLevel() >= 1 && monster.getLevel() <= 4)
				&& (Card.getBoard().getActivePlayer() == this)) {
			this.field.addMonsterToField(monster, Mode.DEFENSE, null);
			monster.setPlayed(true);
			this.setSameTurn(true);
			// monster.setAttacked(true);
			if (this.field.getMonstersArea().contains(monster))
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) throws WrongPhaseException,
			NoMonsterSpaceException,MultipleMonsterAdditionException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if (this.field.getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();
		if(monster.getPlayed()==true||this.sameTurn==true) throw new MultipleMonsterAdditionException();

		if ((this.gameOver == false) && (Card.getBoard().getWinner() == null)
				&& (this.getField().getHand().contains(monster))
				// && (this.field.getPhase() !=Phase.BATTLE)
				&& (Card.getBoard().getActivePlayer() == this)) {

			// if((monster.getPlayed()==false)&&(monster.getLevel()>=1 &&
			// monster.getLevel()<=4)){
			// this.field.addMonsterToField(monster, Mode.DEFENSE, null);
			// monster.setPlayed(true);
			// }

			if ((this.getSameTurn() == false) && (monster.getPlayed() == false)
					&& (monster.getLevel() == 5 || monster.getLevel() == 6)
					&& sacrifices.size() == 1) {
				this.field.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
				monster.setPlayed(true);
				this.setSameTurn(true);
			}

			if ((this.getSameTurn() == false) && (monster.getPlayed() == false)
					&& (monster.getLevel() == 7 || monster.getLevel() == 8)
					&& sacrifices.size() == 2) {
				this.field.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
				monster.setPlayed(true);
				this.setSameTurn(true);
			}
			// this.field.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
		}
		if (this.field.getMonstersArea().contains(monster))
			return true;
		else
			return false;
	}

	@Override
	public boolean setSpell(SpellCard spell) throws WrongPhaseException,
			NoSpellSpaceException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if (this.field.getSpellArea().size() == 5)
			throw new NoSpellSpaceException();
		
		if ((this.gameOver == false) && (Card.getBoard().getWinner() == null)
				&& (spell.getPlayed() == false)
				// && (this.field.getPhase() !=Phase.BATTLE)
				&& (Card.getBoard().getActivePlayer() == this)) {
			this.field.addSpellToField(spell, null, true);
			spell.setPlayed(true);

		}
		if (this.field.getSpellArea().contains(spell))
			return true;
		else
			return false;

	}

	@Override
	public boolean activateSpell(SpellCard spell, MonsterCard monster)
			throws WrongPhaseException, NoSpellSpaceException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if (// (this.getField().getSpellArea().contains(spell))
		(this.gameOver == false) && (Card.getBoard().getWinner() == null)
				&& (Card.getBoard().getActivePlayer() == this)) {
			if (spell.getLocation() .equals( Location.FIELD)) {
				if (spell.getName().equals("Mage Power")
						|| spell.getName().equals("Change Of Heart")
						|| spell.getName().equals("Monster Reborn"))
					this.field.activateSetSpell(spell, monster);
				else
					this.field.activateSetSpell(spell, null);
			} else {
				if (this.getField().getHand().contains(spell)) {
					if (spell.getName().equals("Mage Power")
							|| spell.getName().equals("Change Of Heart")
							|| spell.getName().equals("Monster Reborn")) {
						if (this.field.getSpellArea().size() == 5)
							throw new NoSpellSpaceException();
						this.field.addSpellToField(spell, monster, false);
					} else {
						if (this.field.getSpellArea().size() == 5)
							throw new NoSpellSpaceException();
						this.field.addSpellToField(spell, null, false);
					}
				}
			}
		}
		if (this.field.getGraveyard().contains(spell)) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster,
			MonsterCard opponentMonster) throws WrongPhaseException,
			MonsterMultipleAttackException, DefenseMonsterAttackException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() != Phase.BATTLE)
			throw new WrongPhaseException();
		if(activeMonster.getAttacked()==true) throw new MonsterMultipleAttackException();
		if(activeMonster.getMode()==Mode.DEFENSE) throw new DefenseMonsterAttackException();
		if ((activeMonster.getAttacked() == false)
				&& (this.field.getPhase().equals(Phase.BATTLE))
				&& (this.gameOver == false)
				&& (Card.getBoard().getWinner() == null)
				// && (this.sameTurn == false)
				&& (!Card.getBoard().getOpponentPlayer().getField()
						.getMonstersArea().isEmpty())
				&& (Card.getBoard().getActivePlayer() == this)) {
			if ((this.field.getMonstersArea().contains(activeMonster))
					&& (Card.getBoard().getOpponentPlayer().getField()
							.getMonstersArea().contains(opponentMonster))
					&& (activeMonster.getMode().equals(Mode.ATTACK))) {
				activeMonster.action(opponentMonster);
				// this.setSameTurn(true);
				activeMonster.setAttacked(true);
				
				return true;
			}
			/*
			 * if ((this.field.getMonstersArea().contains(opponentMonster)) &&
			 * (Card.getBoard().getOpponentPlayer()
			 * .getField().getMonstersArea() .contains(activeMonster)) &&
			 * (opponentMonster.getMode().equals(Mode.ATTACK))) {
			 * opponentMonster.action(activeMonster); // this.sameTurn = true;
			 * 
			 * return true; }
			 */
		}
		
		return false;
	}

	// if(this.field.getPhase()==Phase.MAIN1)
	// return false;

	@Override
	public boolean declareAttack(MonsterCard activeMonster)
			throws WrongPhaseException, MonsterMultipleAttackException,
			DefenseMonsterAttackException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() != Phase.BATTLE)
			throw new WrongPhaseException();
		if(activeMonster.getAttacked()==true) throw new MonsterMultipleAttackException();
		if(activeMonster.getMode()==Mode.DEFENSE) throw new DefenseMonsterAttackException();
		if ((activeMonster.getAttacked() == false)
				&& (this.field.getPhase().equals(Phase.BATTLE))
				&& (this.gameOver == false)
				&& (Card.getBoard().getWinner() == null)
				// && (this.sameTurn == false)
				&& (Card.getBoard().getActivePlayer() == this)
				&& (Card.getBoard().getOpponentPlayer().getField()
						.getMonstersArea().isEmpty())) {

			if ((this.field.getMonstersArea().contains(activeMonster))
					&& (activeMonster.getMode().equals(Mode.ATTACK))) {
				activeMonster.action();
				// this.setSameTurn(true);
				activeMonster.setAttacked(true);
				return true;
			}
		}
		return false;
	}

	@Override
	public void addCardToHand() {
		// TODO Auto-generated method stub
		if ((this.gameOver == false)
				&& (Card.getBoard().getActivePlayer() == this)
				&& (this.field.getDeck().getDeck().size() > 0))
			this.getField().addCardToHand();
		else {
			Card.getBoard().getActivePlayer().setLifePoints(0);
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		}

	}

	@Override
	public void addNCardsToHand(int n) throws WrongPhaseException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if ((this.gameOver == false)
		// && (Card.getBoard().getActivePlayer() == this)
				&& (this.field.getDeck().getDeck().size() >= n)) {
			if (this.field.getPhase() != Phase.BATTLE)
				this.getField().addNCardsToHand(n);
		}

		else {
			Card.getBoard().getActivePlayer().setLifePoints(0);
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		}

	}

	@Override
	public void endPhase() {
		// TODO Auto-generated method stub
		if (this.gameOver == false) {
			if (this.field.getPhase().equals(Phase.MAIN1))
				this.field.setPhase(Phase.BATTLE);
			else if (this.field.getPhase().equals(Phase.BATTLE))
				this.field.setPhase(Phase.MAIN2);
			else if (this.field.getPhase().equals(Phase.MAIN2))
				this.endTurn();
		}

	}

	@Override
	public boolean endTurn() {
		// TODO Auto-generated method stub
		if ((this.gameOver == false)
				&& (this == Card.getBoard().getActivePlayer())) {
			// && (Card.getBoard().getWinner() == null)) {
			Card.getBoard().nextPlayer();
			this.field.setPhase(Phase.MAIN1);
			this.setSameTurn(false);
			for (int i = 0; i < this.getField().getMonstersArea().size(); i++) {
				this.getField().getMonstersArea().get(i).setAttacked(false);
			}

			return true;
		}
		return false;

	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster)
			throws WrongPhaseException {
		// TODO Auto-generated method stub
		if (this.field.getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		if ((this.gameOver == false) && (monster.getPlayed() == true)
				&& (monster.getSwitched() == false)
				&& (Card.getBoard().getActivePlayer() == this)
				&& (Card.getBoard().getWinner() == null)
				// && (this.getSameTurn() == false)
				// && (this.field.getPhase() !=Phase.BATTLE)
				&& this.field.getMonstersArea().contains(monster)) {

			if (monster.getMode() == Mode.ATTACK) {
				monster.setMode(Mode.DEFENSE);
				monster.setSwitched(true);
				return true;
				// this.setSameTurn(true);
			} else {
				if (monster.getMode() == Mode.DEFENSE) {
					monster.setMode(Mode.ATTACK);
					monster.setSwitched(true);
					// this.setSameTurn(true);
				}

				return true;
			}
		}
		return false;
	}

	public Boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}
	

}
