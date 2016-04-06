package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster{
	
	public HeavyStorm(String name, String description)
	{
		super(name, description);
	}
	
	public void action(MonsterCard monster)
	{
		super.action(null);
		
		getBoard().getActivePlayer().getField().removeSpellToGraveyard(getBoard().getActivePlayer().getField().getSpellArea());
		//getBoard().getActivePlayer().getField().removeSpellToGraveyard(this);
	}

}
