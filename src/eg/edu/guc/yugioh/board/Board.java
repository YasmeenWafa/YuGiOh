package eg.edu.guc.yugioh.board;

import java.io.IOException;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.gui.GUI;
import eg.edu.guc.yugioh.listeners.Controller;

public class Board {
	
	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner = null;
	//private boolean sameTurn = false;

	public Board()
	{
		Card.setBoard(this);	
	}
	public void whoStarts(Player p1, Player p2)
	{
		int x = (int)(Math.random()*2);;
		if (x==0)
		{	this.activePlayer = p1;
		    this.opponentPlayer = p2;
		}
		else {
			this.activePlayer = p2;
			this.opponentPlayer=p1;
		    }
		}
	public void startGame(Player p1, Player p2)
	{
		whoStarts(p1,p2);
		p1.addNCardsToHand(5);;
		p2.addNCardsToHand(5);
		if (this.activePlayer.equals(p1))
		{
			p1.addCardToHand();
		}
		else p2.addCardToHand();
		
	}
	public void nextPlayer()
	{if(winner == null)
		//if(this.activePlayer.getGameOver()==false)
	{
		Player x = this.activePlayer;
	  this.setActivePlayer(this.opponentPlayer);
	  this.setOpponentPlayer(x);
	  if(this.activePlayer.getField().getDeck().getDeck().size()!=0)
	  this.activePlayer.addCardToHand();
	  else setWinner(this.opponentPlayer);
	}
	else this.activePlayer.setGameOver(true);
	
		
	  //this.opponentPlayer.getField().getDeck().drawOneCard();
		
	}
	public Player getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}
	public Player getOpponentPlayer() {
		return opponentPlayer;
	}
	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}
	public Player getWinner()
	{
	//	if(this.getActivePlayer().getGameOver()==true)
		return winner;
		//return null;
	}
	public void setWinner(Player winner) {
		if(this.activePlayer.getGameOver()==false)
		this.winner = winner;
		//this.opponentPlayer.setLifePoints(0);
		this.activePlayer.setGameOver(true);
	}
	
	/*public Boolean getSameTurn()
	{
		return this.sameTurn;
	}
	public void setSameTurn(Boolean flag)
	{
		this.sameTurn=flag;
	}*/
	public static void main(String[] args) throws IOException {
		//int x = (int)(Math.random()*2);
		//System.out.println(x);
		/*Board x = new Board();
		Player p1 = new Player("mima");
		Player p2 = new Player("lolo");
		//x.whoStarts(p1, p2);
		x.startGame(p1, p2);
		int y = x.activePlayer.getField().getHand().size();
		System.out.println(y);*/
		//Board x = new Board();
	}
}
