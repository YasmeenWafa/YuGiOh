package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class ChangeOfHeart extends SpellCard{
	
	public ChangeOfHeart(String name, String description)
	{
		super(name, description);
	}
	public void action(MonsterCard monster)
	{
		
		
		getBoard().getOpponentPlayer().getField().getMonstersArea().remove(monster);
		getBoard().getActivePlayer().getField().getMonstersArea().add(monster);
		//getBoard().getActivePlayer().getField().removeSpellToGraveyard(this);
		
	}

}
