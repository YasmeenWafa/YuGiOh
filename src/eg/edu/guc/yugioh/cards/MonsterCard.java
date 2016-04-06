package eg.edu.guc.yugioh.cards;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class MonsterCard extends Card {

	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode = Mode.DEFENSE;
	private Boolean switched = false;
	private Boolean attacked = false;

	public Boolean getAttacked() {
		return attacked;
	}

	public void setAttacked(Boolean attacked) {
		this.attacked = attacked;
	}

	public Boolean getSwitched() {
		return switched;
	}

	public void setSwitched(Boolean switched) {
		this.switched = switched;
	}

	public MonsterCard(String name, String description, int level, int attack,
			int defence) {
		super(name, description);
		this.level = level;
		this.attackPoints = attack;
		this.defensePoints = defence;
	}

	public int getLevel() {
		return this.level;
	}

	public int getDefensePoints() {
		return this.defensePoints;
	}

	public int getAttackPoints() {
		return this.attackPoints;
	}

	public Mode getMode() {
		return this.mode;
	}

	public void setAttackPoints(int extraPoints) {
		this.attackPoints = extraPoints;
	}

	public void setDefensePoints(int extraPoints) {
		this.defensePoints = extraPoints;
	}

	public void setMode(Mode newMode) {
		this.mode = newMode;
	}

	public void action(MonsterCard monster)
			throws MonsterMultipleAttackException,
			DefenseMonsterAttackException, WrongPhaseException {

		// NOT COMPLETE!
		if ((getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE)
				&& (this.getMode().equals(Mode.ATTACK))
				&& (monster.getMode() == Mode.ATTACK)
				&& (getBoard().getOpponentPlayer().getField().getMonstersArea()
						.contains(monster))
				&& getBoard().getActivePlayer().getField().getMonstersArea()
						.contains(this)) {
			if (this.getAttackPoints() > monster.getAttackPoints()) {
				int diff = this.getAttackPoints() - monster.getAttackPoints();
				getBoard().getOpponentPlayer().setLifePoints(
						getBoard().getOpponentPlayer().getLifePoints() - diff);
				getBoard().getOpponentPlayer().getField()
						.removeMonsterToGraveyard(monster);
				if (getBoard().getOpponentPlayer().getLifePoints() <= 0) {
					getBoard().getOpponentPlayer().setLifePoints(0);
					// getBoard().getActivePlayer().setLifePoints(0);
					getBoard().setWinner(getBoard().getActivePlayer());
				}
			} else {
				if (this.getAttackPoints() < monster.getAttackPoints()) {
					int diff = monster.getAttackPoints()
							- this.getAttackPoints();
					getBoard().getActivePlayer()
							.setLifePoints(
									getBoard().getActivePlayer()
											.getLifePoints() - diff);
					getBoard().getActivePlayer().getField()
							.removeMonsterToGraveyard(this);
					if (getBoard().getActivePlayer().getLifePoints() <= 0) {
						getBoard().getActivePlayer().setLifePoints(0);
						// getBoard().getOpponentPlayer().setLifePoints(0);
						getBoard().setWinner(getBoard().getOpponentPlayer());
					}
				} else {
					getBoard().getActivePlayer().getField()
							.removeMonsterToGraveyard(this);
					getBoard().getOpponentPlayer().getField()
							.removeMonsterToGraveyard(monster);
				}
			}
		}

		if ((this.getMode().equals(Mode.ATTACK))
				&& (getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE)
				&& (monster.getMode() == Mode.DEFENSE)
				&& (getBoard().getOpponentPlayer().getField().getMonstersArea()
						.contains(monster))
				&& getBoard().getActivePlayer().getField().getMonstersArea()
						.contains(this))

		{
			if (this.attackPoints > monster.getDefensePoints()) {
				getBoard().getOpponentPlayer().getField()
						.removeMonsterToGraveyard(monster);
			} else {
				if (this.attackPoints < monster.getDefensePoints()) {
					int diff = monster.getDefensePoints() - this.attackPoints;
					getBoard().getActivePlayer()
							.setLifePoints(
									getBoard().getActivePlayer()
											.getLifePoints() - diff);
					if (getBoard().getActivePlayer().getLifePoints() <= 0) {
						getBoard().getActivePlayer().setLifePoints(0);
						// getBoard().getOpponentPlayer().setLifePoints(0);
						getBoard().setWinner(getBoard().getOpponentPlayer());
					}
				}
			}

		}

		if ((getBoard().getOpponentPlayer().getLifePoints() <= 0)
				|| getBoard().getOpponentPlayer().getField().getDeck()
						.getDeck().isEmpty()) {
			getBoard().setWinner(getBoard().getActivePlayer());
		}
		if ((getBoard().getActivePlayer().getLifePoints() <= 0)
				|| getBoard().getActivePlayer().getField().getDeck().getDeck()
						.isEmpty()) {
			getBoard().getActivePlayer().setLifePoints(0);
			// getBoard().getOpponentPlayer().setLifePoints(0);
			getBoard().setWinner(getBoard().getOpponentPlayer());
		}

	}

	public void action() {

		/*
		 * if
		 * (getBoard().getOpponentPlayer().getField().getMonstersArea().isEmpty
		 * ()) {
		 * while(getBoard().getActivePlayer().getField().getMonstersArea().
		 * size()!=0) { if ((getBoard().getActivePlayer().getField().getPhase()
		 * == Phase.BATTLE) && (getBoard().getActivePlayer().getField()
		 * .getMonstersArea().get(0).getMode() == Mode.ATTACK) &&
		 * (getBoard().getActivePlayer().getField()
		 * .getMonstersArea().get(0).getLocation() == Location.FIELD))
		 * {this.getBoard
		 * ().getOpponentPlayer().setLifePoints(this.getBoard().getOpponentPlayer
		 * ().getLifePoints()-
		 * getBoard().getActivePlayer().getField().getMonstersArea
		 * ().get(0).getAttackPoints()); }
		 * getBoard().getActivePlayer().getField(
		 * ).removeMonsterToGraveyard(getBoard
		 * ().getActivePlayer().getField().getMonstersArea().remove(0)); } }
		 */
		if ((getBoard().getActivePlayer().getField().getPhase()
				.equals(Phase.BATTLE)) && (this.getMode() == Mode.ATTACK))
			getBoard().getOpponentPlayer().setLifePoints(
					getBoard().getOpponentPlayer().getLifePoints()
							- this.getAttackPoints());
		if ((getBoard().getOpponentPlayer().getLifePoints() <= 0)
				|| getBoard().getOpponentPlayer().getField().getDeck()
						.getDeck().isEmpty()) {
			getBoard().getOpponentPlayer().setLifePoints(0);
			// getBoard().getActivePlayer().setLifePoints(0);
			getBoard().setWinner(getBoard().getActivePlayer());
		}
	}
}
