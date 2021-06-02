package testing;

import gameComponents.Player;
import gameComponents.Token;
import gameComponents.Color;
import customException.FullColumnException;
import gameComponents.Board;
import gameComponents.Match;
import saveAndLoad.SaveMatch;

/**
 * Class for testing methods and behavior of the class SaveMatch, as this class name suggests.
 * This is fundamentally a unit testing.
 * In the class, it will be created a file containing the saved match 
 * (in the format described in the SaveMatch class).
 * The file will be available once run this main.
 * The name of the file is testSaveFile.txt.
 * 
 * The expected strings of the file will be prompted when launching.
 * 
 * Talking about the structure of the class, each test has got its static method.
 * Each method is then invoked in the main.
 */
public class SaveTester {
	
	public static void main(String...strings) {
		

		Match match = createTestMatch();
		String testFileName = "testSaveFile";
		
		System.out.println("Testing...");
		SaveMatch save = new SaveMatch(testFileName, match);
		System.out.println("File has been created.\n"
				+ "Expected: \n"
				+ "First player: Andrea, token color: 0 0 0\r\n" + 
				"Second player: Erica, token color: 20 220 20\r\n" + 
				"Turn: 7 \r\n" + 
				"Board: \r\n" + 
				"Row: 5 Col: 0 0 0 0\r\n" + 
				"Row: 5 Col: 1 0 0 0\r\n" + 
				"Row: 5 Col: 2 0 0 0\r\n" + 
				"Row: 5 Col: 4 20 220 20\r\n" + 
				"Row: 5 Col: 5 20 220 20\r\n" + 
				"Row: 4 Col: 2 20 220 20\n");
		
		System.out.println("---------------------------------------------------------------------\n");

	}

	/**
	 * Creates a simple, not completed, not winning, not interactively simulated match.
	 * Because of this, turn variable is incremented manually:
	 * in the correct simulation, players interact with the GUI, and the turn variable is
	 * incremented automatically. This scenario is out of the scope of this unit test, 
	 * that only wants to simulate the saving in the file.
	 * 
	 * Only meant for testing.
	 * @return the test match.
	 */
	public static Match createTestMatch() {
		Token t1 = new Token(new Color(0, 0, 0));
		String p1Name = "Andrea";
		Player p1 = new Player(p1Name, t1);
		
		Token t2 = new Token(new Color(20, 220, 20));
		String p2Name = "Erica";
		Player p2 = new Player(p2Name, t2);
		
		Board b = new Board();
		
		int turn = 1;
		Match m = new Match(b, p1, p2);
		
		//Try catch outside the purpose of this class but inserted to escape compile time errors.
		try {
			b.insert(t1, 0);
			turn++;
			b.insert(t2, 5);
			turn++;
			b.insert(t1, 2);
			turn++;
			b.insert(t2, 2);
			turn++;
			b.insert(t1, 1);
			turn++;
			b.insert(t2, 4);
			turn++;
			m.setTurn(turn);
		} catch (FullColumnException e) {
		}
		
		return m;
		
	}
}
