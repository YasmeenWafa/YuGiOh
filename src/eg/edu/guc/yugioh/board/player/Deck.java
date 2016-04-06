package eg.edu.guc.yugioh.board.player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Deck {
	private static ArrayList<Card> monsters = new ArrayList<Card>(30);
	private static ArrayList<Card> spells = new ArrayList<Card>(10);
	private ArrayList<Card> deck = new ArrayList<Card>(20);
	private int tries = 0;
	private static String monstersPath = "Database-Monsters.csv";
	private static String spellsPath = "Database-Spells.csv";
	Scanner sc = new Scanner(System.in);

	public Deck() throws IOException, MissingFieldException,
			UnknownCardTypeException, UnknownSpellCardException,
			EmptyFieldException {

		// TODO, CATCH THE EXCEPTIONS THROWN BY LOADCARDSFROMFILE, AND HANDLE
		// THEM, PREFERABLY USING A LOOP TO THREE, ONCE YOU REACH THREE, YOU
		// THROW THE EXCEPTION THAT YOU CAUGHT.

		while (true) {
			try {

				monsters = loadCardsFromFile(monstersPath);
				break;
			}

			catch (FileNotFoundException e) {
				// TODO TAKE OTHER INPUT ROUTE FROM THE USER IF TRIES < 3, ELSE
				
				if (tries < 3) {

					System.out.println("Please enter a correct path: "
							+ monstersPath + "\n" + "The file was not found");
					setMonstersPath(sc.nextLine());
					tries++;
					
				} else {
					e.printStackTrace();
					throw e;
				}
			} 
			catch(UnexpectedFormatException e)
			{
				if(tries<3)
				{
					System.out.println("Please enter a correct path: " + monstersPath);
					setMonstersPath(sc.nextLine());
					tries++;
					
				}
				else
				{
					e.printStackTrace();
					throw e;
				}
			}
			
		}

		// TODO CATCH ALL THE OTHER EXCEPTIONS AND HANDLE THEM PROPERLY, THEN DO
		// ALL OF THAT AGAIN FOR THE SPELL PART.
		while (true) {
			try {
				spells = loadCardsFromFile(spellsPath);
				break;
			} catch (FileNotFoundException e) {
				// TODO TAKE OTHER INPUT ROUTE FROM THE USER IF TRIES < 3, ELSE
				// throw e.
				// e = new FileNotFoundException();
				if (tries < 3) {

					System.out.println("Please enter a correct path: "
							+ spellsPath + "\n" + "The file was not found");
					setSpellsPath(sc.nextLine());
					tries++;

					// spells = loadCardsFromFile(getSpellsPath());
					// new Deck();
				} else {

					e.printStackTrace();
					throw e;

				}
			}
				catch(UnexpectedFormatException e)
				{
					if(tries<3)
					{
						System.out.println("Please enter a correct path: " + spellsPath);
						setSpellsPath(sc.nextLine());
						tries++;
					//	spells = loadCardsFromFile(getSpellsPath());
					}
					else
					{
						e.printStackTrace();
						throw e;
					}
				}
			

		}
		buildDeck(monsters, spells);
		shuffleDeck();

	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public static String getMonstersPath() {
		return monstersPath;
	}

	public static void setMonstersPath(String monsterPath) {
		monstersPath = monsterPath;
	}

	public static String getSpellsPath() {
		return spellsPath;
	}

	public static void setSpellsPath(String spellPath) {
		spellsPath = spellPath;
	}

	// TODO THROW THE EXCEPTIONS WHENEVER YOU DETECT AN ERROR.
	public ArrayList<Card> loadCardsFromFile(String path) throws IOException,
			FileNotFoundException, MissingFieldException,
			UnknownCardTypeException, UnknownSpellCardException,
			EmptyFieldException {
		ArrayList<Card> res = new ArrayList<Card>();

		String currentLine = "";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		Boolean flag = false;
		int lineNumber = 1;
		while ((currentLine = br.readLine()) != null) {
			// Parsing the currentLine String
			String[] result = currentLine.split(",");
			// System.out.println(currentLine);
			for (int i = 0; i < result.length; i++) {

				
				if (path == monstersPath && result.length == 6) {
					if (result[0] == "" || result[0] == " ")
						throw new EmptyFieldException(path, lineNumber, 1);
					if (result[1].equals("") || result[1].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 2);
					if (result[2].equals("") || result[2].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 3);
					if (result[3].equals("") || result[3].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 4);
					if (result[4].equals("") || result[4].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 5);
					if (result[5].equals("") || result[5].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 6);

				}
				if (path == spellsPath && result.length == 3) {
					if (result[0].equals("") || result[0].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 1);
					if (result[1].equals("") || result[1].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 2);
					if (result[2].equals("") || result[2].equals(" "))
						throw new EmptyFieldException(path, lineNumber, 3);
				}
				if (path == monstersPath && !(result[0].equals("Monster"))) {
					throw new UnknownCardTypeException(path, lineNumber,
							result[0]);
				}
				if (path == spellsPath && !(result[0].equals("Spell"))) {
					throw new UnknownCardTypeException(path, lineNumber,
							result[0]);
				}
				if (path == monstersPath && result.length != 6) {
					throw new MissingFieldException(path, lineNumber);
				}
				if (path == spellsPath && result.length != 3) {
					throw new MissingFieldException(path, lineNumber);
				}
				

				if (result[0].equals("Spell")) {
					String s = result[1];

					switch (s) {
					case ("Card Destruction"):
						CardDestruction x = new CardDestruction(result[1],
								result[2]);
						res.add(x);
						flag = true;
						break;

					case ("Change Of Heart"):
						ChangeOfHeart y = new ChangeOfHeart(result[1],
								result[2]);
						res.add(y);
						break;
					case ("Dark Hole"):
						DarkHole z = new DarkHole(result[1], result[2]);
						res.add(z);
						break;
					case ("Graceful Dice"):
						GracefulDice k = new GracefulDice(result[1], result[2]);
						res.add(k);
						break;
					case ("Harpie's Feather Duster"):
						HarpieFeatherDuster a = new HarpieFeatherDuster(
								result[1], result[2]);
						res.add(a);
						break;
					case ("Heavy Storm"):
						HeavyStorm b = new HeavyStorm(result[1], result[2]);
						res.add(b);
						break;
					case ("Mage Power"):
						MagePower c = new MagePower(result[1], result[2]);
						res.add(c);
						break;
					case ("Monster Reborn"):
						MonsterReborn d = new MonsterReborn(result[1],
								result[2]);
						res.add(d);
						break;
					case ("Pot of Greed"):
						PotOfGreed e = new PotOfGreed(result[1], result[2]);
						res.add(e);
						break;
					case ("Raigeki"):
						Raigeki f = new Raigeki(result[1], result[2]);
						res.add(f);
						break;
					default:
						flag = false;
						//if(tries<3)
						throw new UnknownSpellCardException(path, lineNumber,
								result[1]);
					}

					break;
				}

				if (result[0].equals("Monster")) {

					MonsterCard x = new MonsterCard(result[1], result[2],
							Integer.parseInt(result[5]),
							Integer.parseInt(result[3]),
							Integer.parseInt(result[4]));
					res.add(x);
					break;
				}
			}
			lineNumber++;
		}

		return res;
	}

	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells)
			throws IOException {

		int count = 0;
		while (count < 15) {
			int x = (int) (Math.random() * monsters.size());

			MonsterCard y = new MonsterCard(monsters.get(x).getName(), monsters
					.get(x).getDescription(),
					((MonsterCard) monsters.get(x)).getLevel(),
					((MonsterCard) monsters.get(x)).getAttackPoints(),
					((MonsterCard) monsters.get(x)).getDefensePoints());
			this.deck.add(y);
			count++;
		}

		int count2 = 0;
		while (count2 < 5) {
			int y = ((int) (Math.random() * spells.size()));

			String s = spells.get(y).getName();
			switch (s) {
			case ("Card Destruction"):
				CardDestruction x = new CardDestruction(
						spells.get(y).getName(), spells.get(y).getDescription());
				this.deck.add(x);
				break;
			case ("Change Of Heart"):
				ChangeOfHeart l = new ChangeOfHeart(spells.get(y).getName(),
						spells.get(y).getDescription());
				this.deck.add(l);
				break;
			case ("Dark Hole"):
				DarkHole z = new DarkHole(spells.get(y).getName(), spells
						.get(y).getDescription());
				this.deck.add(z);
				break;
			case ("Graceful Dice"):
				GracefulDice k = new GracefulDice(spells.get(y).getName(),
						spells.get(y).getDescription());
				this.deck.add(k);
				break;
			case ("Harpie's Feather Duster"):
				HarpieFeatherDuster a = new HarpieFeatherDuster(spells.get(y)
						.getName(), spells.get(y).getDescription());
				this.deck.add(a);
				break;
			case ("Heavy Storm"):
				HeavyStorm b = new HeavyStorm(spells.get(y).getName(), spells
						.get(y).getDescription());
				this.deck.add(b);
				break;
			case ("Mage Power"):
				MagePower c = new MagePower(spells.get(y).getName(), spells
						.get(y).getDescription());
				this.deck.add(c);
				break;
			case ("Monster Reborn"):
				MonsterReborn d = new MonsterReborn(spells.get(y).getName(),
						spells.get(y).getDescription());
				this.deck.add(d);
				break;
			case ("Pot of Greed"):
				PotOfGreed e = new PotOfGreed(spells.get(y).getName(), spells
						.get(y).getDescription());
				this.deck.add(e);
				break;
			case ("Raigeki"):
				Raigeki f = new Raigeki(spells.get(y).getName(), spells.get(y)
						.getDescription());
				this.deck.add(f);
				break;
			}

			count2++;
		}

	}

	private void shuffleDeck() {
		Collections.shuffle(this.deck);

	}

	public ArrayList<Card> drawNCards(int n) {
		ArrayList<Card> x = new ArrayList<Card>(n);

		while (n != 0) {
			x.add(this.deck.remove(0));
			n--;
		}

		return x;
	}

	public Card drawOneCard() {

		Card x = this.deck.remove(0);

		return x;

	}

	public ArrayList<Card> getDeck() {
		return this.deck;
	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public static void main(String[] args) throws MissingFieldException,
			UnknownCardTypeException, UnknownSpellCardException,
			EmptyFieldException, IOException {

		Deck x = new Deck();
		// for (int i = 0; i<x.deck.size(); i++)
		// System.out.println(x.deck.get(i).getName());
		// x.loadCardsFromFile("Database-Monstrs.csv");
		// x.buildDeck(x.monsters, x.spells);
		// x.shuffleDeck();
		// x.drawOneCard();
		// for (int i =0; i<x.deck.size(); i++)
		// {
		// System.out.println(x.deck.get(i).getName()+ " "+
		// x.deck.get(i).getDescription());
		// }

	}

}