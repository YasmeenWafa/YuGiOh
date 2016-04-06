package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard
{
	
	public CardDestruction(String name, String description)
	{
		super(name, description);
	
		
	}
	public void action(MonsterCard monster)
	{
		int activeHand = getBoard().getActivePlayer().getField().getHand().size();
		while(getBoard().getActivePlayer().getField().getHand().size()!=0)
		{
			
			 getBoard().getActivePlayer().getField().removeCardFromHand( getBoard().getActivePlayer().getField().getHand().get(0));
			
		}
           getBoard().getActivePlayer().addNCardsToHand(activeHand);
		
		int opponentHand = getBoard().getOpponentPlayer().getField().getHand().size();
	
		while(getBoard().getOpponentPlayer().getField().getHand().size()!=0)
		{
			
		 getBoard().getOpponentPlayer().getField().removeCardFromHand( getBoard().getOpponentPlayer().getField().getHand().get(0));
		}
		getBoard().getOpponentPlayer().addNCardsToHand(opponentHand);
	}
	

}
