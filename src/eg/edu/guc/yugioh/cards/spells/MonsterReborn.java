package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard{
	
	public MonsterReborn(String name, String description)
	{
		super(name, description);
	}
	
	public void action(MonsterCard monster)
	{
		
	   MonsterCard fromactivegraveyard = getBoard().getActivePlayer().getField().getStrongestMonsterInGraveyard(getBoard().getActivePlayer().getField().getGraveyard());
		MonsterCard fromopponentgraveyard= getBoard().getActivePlayer().getField().getStrongestMonsterInGraveyard(getBoard().getOpponentPlayer().getField().getGraveyard());
		if (fromactivegraveyard.getAttackPoints()>fromopponentgraveyard.getAttackPoints())
		{
			getBoard().getActivePlayer().getField().getGraveyard().remove(fromactivegraveyard);
			getBoard().getActivePlayer().getField().getMonstersArea().add(fromactivegraveyard);
			fromactivegraveyard.setLocation(Location.FIELD);
			
			//getBoard().getOpponentPlayer().getField().getGraveyard().add(fromopponentgraveyard);
		}
		else {
			 getBoard().getOpponentPlayer().getField().getGraveyard().remove(fromopponentgraveyard);
			getBoard().getActivePlayer().getField().getMonstersArea().add(fromopponentgraveyard);
			fromopponentgraveyard.setLocation(Location.FIELD);
		   
		    //getBoard().getActivePlayer().getField().getGraveyard().add(fromactivegraveyard);
			}
		
		
	//	getBoard().getActivePlayer().getField().removeSpellToGraveyard(this);
	}
}
