package eg.edu.guc.yugioh.cards;

import eg.edu.guc.yugioh.board.Board;

public abstract class Card {
	
	private  String name;
	private  String description;
	private boolean isHidden = true;
	private Location location=Location.DECK;
	private static Board board=new Board();
	private boolean played = false;

	
	public Card(String name, String description)
	{
		 this.name = name;
		 this.description = description;
		 isHidden = true;
		 
    }
	abstract public void action(MonsterCard monster);
	
	public String getName()
	{
		return this.name;
	}
	public String getDescription()
	{
		return this.description;
	}
	public boolean isHidden()
	{
		return this.isHidden;
	}
	public Location getLocation()
	{
		return this.location;
	}
	
 
	public void setLocation(Location newLocation)
	{
		this.location = newLocation;
	}
	public void setHidden(Boolean isHidden)
	{
		this.isHidden = isHidden;
	}
	public static Board getBoard()
	{
		return board;
	}
	public static void setBoard(Board b)
	{
		board = b;
	}
	public Boolean getPlayed()
	{
		return this.played;
	}
	public Boolean setPlayed(Boolean flag)
	{
		return this.played=flag;
	}
		
	
	

}
