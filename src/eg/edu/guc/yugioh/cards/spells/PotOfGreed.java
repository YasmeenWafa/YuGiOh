package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class PotOfGreed extends SpellCard{
	
	public PotOfGreed(String name, String description)
	{
		super(name, description);
	}
	
	public void action(MonsterCard monster)
	{
		//if(getBoard().getActivePlayer().getField().getPhase()==Phase.MAIN1 ||getBoard().getActivePlayer().getField().getPhase()==Phase.MAIN2)
	     getBoard().getActivePlayer().addNCardsToHand(2);
	//getBoard().getActivePlayer().getField().removeSpellToGraveyard(this);
		
		//monster = null;
	}

}
