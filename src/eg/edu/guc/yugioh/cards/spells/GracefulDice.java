package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard{
	
	public GracefulDice(String name, String description)
	{
		super(name, description);
	}
	
	public void action(MonsterCard monster)
	{
		int extraPoints = (int) (1+Math.random()*10);
		for (int i = 0; i< getBoard().getActivePlayer().getField().getMonstersArea().size(); i++)
		{
			getBoard().getActivePlayer().getField().getMonstersArea().get(i).setAttackPoints(getBoard().getActivePlayer().getField().getMonstersArea().get(i).getAttackPoints()+extraPoints*100);
			getBoard().getActivePlayer().getField().getMonstersArea().get(i).setDefensePoints(getBoard().getActivePlayer().getField().getMonstersArea().get(i).getDefensePoints()+extraPoints*100);

		}
		//getBoard().getActivePlayer().getField().removeSpellToGraveyard(this);
	}
	
	

}
