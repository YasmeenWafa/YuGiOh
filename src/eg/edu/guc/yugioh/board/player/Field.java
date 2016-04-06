package eg.edu.guc.yugioh.board.player;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.exceptions.*;

public class Field {

	private Phase phase = Phase.MAIN1;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private Deck deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;
	

	

	public Field() throws MissingFieldException,
	UnknownCardTypeException, UnknownSpellCardException,
	EmptyFieldException, IOException {
		this.monstersArea = new ArrayList<MonsterCard>(5);
		this.spellArea = new ArrayList<SpellCard>(5);
		this.hand = new ArrayList<Card>();
		this.graveyard = new ArrayList<Card>();
		this.deck = new Deck();
	}

	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden)throws NoMonsterSpaceException,WrongPhaseException {
	
		
		if (!(this.monstersArea.size()<5)) throw new NoMonsterSpaceException(); 
				
		if(monster.getLocation()==Location.HAND)
		{
			
			this.hand.remove(monster);
			this.monstersArea.add(monster);
			monster.setMode(m);
			if(m==Mode.ATTACK)
			monster.setHidden(false);
			else monster.setHidden(true);
			monster.setLocation(Location.FIELD);
			
		}
	}

	
	  public void addMonsterToField(MonsterCard monster, Mode mode, ArrayList<MonsterCard>sacrifices)throws NoMonsterSpaceException,WrongPhaseException
	  {
		
			  /*if((monster.getLevel() >= 1)&&(monster.getLevel() <=4)){ 
				 
				  if (mode == Mode.ATTACK)
				  addMonsterToField(monster,mode, false);
				  else addMonsterToField(monster,mode,true);
			  }
			  if((monster.getLevel()==5) || monster.getLevel()==6)
			  {
				  monstersArea.remove(sacrifices.get(0));
				  this.removeMonsterToGraveyard(sacrifices.get(0));
				  if(mode== Mode.ATTACK)
					  addMonsterToField(monster,mode, false);
				  else addMonsterToField(monster,mode,true);
			  }
			  if((monster.getLevel()==7) || monster.getLevel()==8){
			  monstersArea.remove(sacrifices.get(0));
			  monstersArea.remove(sacrifices.get(1));
			  removeMonsterToGraveyard(sacrifices);
			  if(mode==Mode.ATTACK)
				  addMonsterToField(monster,mode, false);
			  else addMonsterToField(monster,mode,true);
			  }*/
		  this.hand.remove(monster);
		  monster.setHidden((mode == Mode.DEFENSE) ? true : false);
			monster.setMode(mode);
			monster.setLocation(Location.FIELD);
			if (sacrifices != null)
				removeMonsterToGraveyard(sacrifices);
			monstersArea.add(monster);
			  
		
	  }
	 
	public void removeMonsterToGraveyard(MonsterCard monster) {
		if (monster.getLocation() == Location.FIELD && monstersArea.contains(monster)) {
			monster.setLocation(Location.GRAVEYARD);
			this.monstersArea.remove(monster);
			this.graveyard.add(monster);
	   }
		/*if(monster.getLocation()==Location.HAND && hand.contains(monster))
		{
			monster.setLocation(Location.GRAVEYARD);
			this.hand.remove(monster);
			this.graveyard.add(monster);
		}*/
	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {
		while(monsters.size()!=0)
		{
			//monsters.get(0).setLocation(Location.GRAVEYARD);
			//this.monstersArea.remove(monsters.get(0));
			//this.graveyard.add(monsters.remove(0));
			//monsters.remove(0);
			removeMonsterToGraveyard(monsters.remove(0));
		}
	}

	public void addSpellToField(SpellCard action, MonsterCard monster,boolean hidden)throws NoSpellSpaceException,WrongPhaseException {

		if(!(this.spellArea.size()<5)) throw new NoSpellSpaceException(); 
			if(this.phase==Phase.BATTLE) throw new WrongPhaseException();
			if( action.getLocation()==Location.HAND)
		{      
			spellArea.add(action);
			this.hand.remove(action);
			action.setLocation(Location.FIELD);
			if (!hidden)
				activateSetSpell(action, monster);
			
		}
	}
	

	public void activateSetSpell(SpellCard action, MonsterCard monster)throws WrongPhaseException
	    {
		/*if ((action.isHidden()==true) && (action.getLocation()==Location.FIELD)){
		action.action(monster);
		removeSpellToGraveyard(action);
		}
		*/
		if (getSpellArea().contains(action)) {

			action.action(monster);
			removeSpellToGraveyard(action);

		}
		
		}
	
	

	public void removeSpellToGraveyard(SpellCard spell) {
		if(spellArea.contains(spell) && spell.getLocation()==Location.FIELD){
		spell.setLocation(Location.GRAVEYARD);
		spellArea.remove(spell);
		graveyard.add(spell);
		}
		/*if(hand.contains(spell) && spell.getLocation()==Location.HAND)
		{
			spell.setLocation(Location.GRAVEYARD);
			hand.remove(spell);
			graveyard.add(spell);
		}*/
	}
		

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
		while(!spells.isEmpty()){
			
		removeSpellToGraveyard(spells.remove(0));
		}
	
	}
	public Phase getPhase() {
		return this.phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return this.monstersArea;
	}

	public Deck getDeck() {
		return this.deck;
	}

	public ArrayList<Card> getGraveyard() {
		return this.graveyard;
	}

	public void addCardToHand() {
		if ((this.phase == Phase.MAIN1)||(this.phase==Phase.MAIN2))
		{
		
		Card x = this.deck.drawOneCard();
		x.setLocation(Location.HAND);
			this.hand.add(x);
		}

	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public void addNCardsToHand(int n) {
		
		if(this.phase==Phase.MAIN1 || this.phase==Phase.MAIN2)
		{
			ArrayList<Card> x = this.deck.drawNCards(n);
		while(x.size()!=0){
		x.get(0).setLocation(Location.HAND);
		this.hand.add(x.remove(0));
		}
		}
	}

	public ArrayList<SpellCard> getSpellArea() {
		return this.spellArea;
	}
	
	public MonsterCard getStrongestMonsterInGraveyard(ArrayList<Card> x)
	{
		for(int j = 0; j<x.size(); j++)
		{
		if(x.get(j) instanceof MonsterCard)
		 {
		         int largest = ((MonsterCard) x.get(j)).getAttackPoints();
		         int index = j;
		 for(int i =1;i<x.size();i++) {
		 
		    if(x.get(i) instanceof MonsterCard &&((MonsterCard) x.get(i)).getAttackPoints() > largest)
		    {
		      largest = ((MonsterCard) x.get(i)).getAttackPoints();
		      index = i;
		    }
		    }
			return (MonsterCard) x.get(index);
		 }
		}
		 return null;

	}
	
	public void removeCardFromHand(Card c)
	{
		if(c.getLocation()==Location.HAND && hand.contains(c))
		{
			c.setLocation(Location.GRAVEYARD);
			this.hand.remove(c);
			this.graveyard.add(c);
		}
	}
	/*public void removeSpellFromHand(SpellCard spell)
	{
		if(spell.getLocation()==Location.HAND && hand.contains(spell))
		{
			spell.setLocation(Location.GRAVEYARD);
			this.hand.remove(spell);
			this.graveyard.add(spell);
		}
	}*/

	public static void main(String[] args) throws IOException {
		//Field x = new Field();
		// for (int i = 0; i<x.deck.getDeck().size(); i++)
		// System.out.println(x.deck.getDeck().get(i).getName());
		/*for (int i = 0; i<x.hand.size(); i++)
		{
			if (x.hand.get(i).getClass().equals("MonsterCard") && x.phase==Phase.MAIN1)
			{
			   x.addMonsterToField((MonsterCard)x.hand.get(i), Mode.ATTACK, false);
			   System.out.println(x.hand.get(i).getName());
		    }*/
		//x.addNCardsToHand(5);
		//x.getStrongestMonsterInGraveyard(x.hand);
	}

}

