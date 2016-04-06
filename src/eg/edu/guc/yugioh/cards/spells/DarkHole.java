package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki{
	
	public DarkHole(String name, String description)
	{
		super(name, description);
	}
	
	public void action(MonsterCard monster) // on whose field?
	{

		super.action(null);	
		
		while(getBoard().getActivePlayer().getField().getMonstersArea().size()!=0)
		{
			getBoard().getActivePlayer().getField().removeMonsterToGraveyard(getBoard().getActivePlayer().getField().getMonstersArea().remove(0));
		}
		//getBoard().getActivePlayer().getField().removeSpellToGraveyard(this);
		
	}

}
