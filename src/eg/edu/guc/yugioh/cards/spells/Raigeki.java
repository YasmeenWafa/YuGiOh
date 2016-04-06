package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard {
	
	public Raigeki(String name, String description)
	{
		super(name, description);
	}
	
	public void action(MonsterCard monster)
	{
		//monster = null;
		
		//int n = getBoard().getOpponentPlayer().getField().getMonstersArea().size();
		
			getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(getBoard().getOpponentPlayer().getField().getMonstersArea());
			
		}
		
	}


