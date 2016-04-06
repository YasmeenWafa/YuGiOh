package eg.edu.guc.yugioh.listeners;

import java.awt.Color;
import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.gui.GUI;
import eg.edu.guc.yugioh.gui.mouse;

public class Main {

	static GUI gui;
	static Board b;
	static Controller c;
	
	

	public Main() throws MissingFieldException,
	UnknownCardTypeException, UnknownSpellCardException,
	EmptyFieldException, IOException {
		
		b = new Board();
		gui = new GUI();
	 
		
		c = new Controller(b, gui);
		

	}

	

	public static Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}

	public static GUI getGui() {
		return gui;
	}

	public static void setGui(GUI gui) {
		Main.gui = gui;
	}

	public static Board getB() {
		return b;
	}

	public static void setB(Board board) {
		b = board;
	}



	public static void main(String[] args) throws Exception {
		Main x = new Main();

	}

}
