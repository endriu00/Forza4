package testing;

import java.awt.Color;

import customException.FullColumnException;
import gameComponents.Board;
import gameComponents.Match;
import gameComponents.Player;
import gameComponents.Token;


/**
 * Class for testing methods and behavior of the class Match, as this class name suggests.
 * This is fundamentally a unit testing.
 * In the class, it has been written, for each test, the purpose of the test, so a short explanation, 
 * an expected result, and the effective result of the test. Easy to understand, if the expected result
 * and the result itself are the same, the test has been successful.
 * In order to view these tests, you are provided a main method that will show you the testing results 
 * in the standard output view. To not harden the view, it has been provided a catchy view.
 * 
 * Talking about the structure of the class, each test has got its static method.
 * Each method is then invoked in the main.
 * 
 * @author andre
 */
public class MatchTester {

	
	public static void main(String[] args) {
		
		System.out.println("\n\nWELCOME TO THIS UNIT TEST!\n"
				+ "\n"
				+ "Here we will test the behavior of the methods of the class "
				+ "Match\n"
				+ "In the class, it has been written, for each test, the purpose of the test, so a short explanation, \n"
				+ "an expected result, and the effective result of the test.\nEasy to understand, if the expected result \n" 
				+ "and the result itself are the same, the test has been successful.\n\n");
		
		System.out.println("Let's begin!\n\n");
		
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV\n\n\n");
		
		Color aColor = new Color(20, 20, 20);
		Token aToken = new Token(aColor);
		Player firstPlayer = new Player("Hello", aToken);
		
		Color anotherColor = new Color(120, 120, 120);
		Token anotherToken = new Token(anotherColor);
		Player secondPlayer = new Player("You", anotherToken);
		
		Board thisBoard = new Board();
		Match match = new Match(thisBoard, firstPlayer, secondPlayer);
		
		System.out.println("Test for getting the first player.\n"
				+ "For this purpose, it has been initialized a new match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "Testing directly getFirstPlayer() would return something like gameComponents.Player@chars,\n"
				+ "with chars representing the memory location of the object, so, for readability,\n"
				+ "it will be shown player's name.\n"
				+ "Testing Player.getName() can be found in the Player testing class.\n"
				+ "The principle is that, if this method works, methods applied after will work.\n");
		System.out.println("Expected: Hello");
		System.out.println("Testing...");
		testGetFirstPlayer(match);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for getting the second player.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "Testing directly getSecondPlayer() would return something like gameComponents.Player@chars,\n"
				+ "with chars representing the memory location of the object, so, for readability,\n"
				+ "it will be shown player's name.\n"
				+ "Testing Player.getName() can be found in the Player testing class.\n"				
				+ "The principle is that, if this method works, methods applied after will work.\n");

		System.out.println("Expected: You");
		System.out.println("Testing...");
		testGetSecondPlayer(match);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for getting the board of the match.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "Testing directly getBoard() would return something like gameComponents.Board@chars,\n"
				+ "with chars representing the memory location of the object, so, for readability,\n"
				+ "it will be shown the board directly. In order to give a sense to it, firstPlayer's token is added to the board.\n"
				+ "Testing Board.insert() and Board.printBoard() can be found in the Board testing class.\n"
				+ "The principle is that, if this method works, methods applied after will work.\n");
		
		System.out.println("Expected: |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null gameComponents.Token@chars null null null null |");
		System.out.println("Testing...");
		//Exception testing outside the purpose of this class, so not catched properly
		try {
			match.insertInBoard(match.getFirstPlayer().getToken(), 2);
		} catch (FullColumnException e) {
		}
		testGetBoard(match);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for getting the turn of the match.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "Turn is changed during the execution of the game in response to users' moves.\n"
				+ "Here it will return 1, that is the turn initialized in the match constructor.\n");
		
		System.out.println("Expected: 1");
		System.out.println("Testing...");
		testGetTurn(match);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for determining who will play first in the match.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "This method contains a pseudorandom number generator of the class Math for determining almost equally\n"
				+ "if the first player will play first or if the second will, so answer is not clearly predictable.\n"
				+ "True indicates that the firstPlayer will play first, false the opposite\n"
				+ "We will only see if the answer is true or false.\n");
		
		System.out.println("Expected: true or false");
		System.out.println("Testing...");
		testWhoPlaysFirst(match);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for getting if the first player is playing now in the match.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "This method depends on the previous method, that uses a pseudorandom number generator of the class Math for determining almost equally\n"
				+ "if the first player will play first or if the second will, so answer depends on the previous one.\n"
				+ "We will see true if previous test printed true, or false in the contraire. \n");
		
		System.out.println("Expected: true or false depending on previous answer");
		System.out.println("Testing...");
		testGetIsFirstPlayerPlaying(match);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for setting if the first player is playing now in the match.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "We will set this to true, meaning that yes, it is true that the first player is actually playing.\n");
		
		System.out.println("Expected: true");
		System.out.println("Testing...");
		testSetFirstPlayerPlaying(match, true);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");

		System.out.println("Test for setting the first player.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "We will set first player to a new player, with name=George and the same token as the previous one.\n"
				+ "In order to see the result, we will use the getFirstPlayer() method tested above.\n"
				+ "Testing directly getFirstPlayer() would return something like gameComponents.Player@chars,\n" 
				+ "with chars representing the memory location of the object, so, for readability,\\n\"\r\n" + 
				"				+ \"it will be shown player's name.\\n\"\r\n" + 
				"				+ \"Testing Player.getName() can be found in the Player testing class.\\n\"\r\n" + 
				"				+ \"The principle is that, if this method works, methods applied after will work.\\n");
		
		System.out.println("Expected: George");
		System.out.println("Testing...");
		testSetFirstPlayer(match, new Player("George", aToken));
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for setting the second player.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "We will set second player to a new player, with name=Thanos and the same token as the previous one.\n"
				+ "In order to see the result, we will use the getSecondPlayer() method tested above.\n"
				+ "Testing directly getSecondPlayer() would return something like gameComponents.Player@chars,\n" 
				+ "with chars representing the memory location of the object, so, for readability,\\n\"\r\n" + 
				"				+ \"it will be shown player's name.\\n\"\r\n" + 
				"				+ \"Testing Player.getName() can be found in the Player testing class.\\n\"\r\n" + 
				"				+ \"The principle is that, if this method works, methods applied after will work.\\n");
		
		System.out.println("Expected: Thanos");
		System.out.println("Testing...");
		testSetFirstPlayer(match, new Player("Thanos", aToken));
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for setting the board.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = aNewBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "		 anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "We will test match.setTurn(), setting turn to a number. We will then see with getTurn() tested above the result.\n");
		
		System.out.println("Expected: 24");
		System.out.println("Testing...");
		testSetTurn(match, 24);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for setting the turn.\n"
				+ "For this purpose, it has been initialized a match with\n"
				+ "firstPlayer = [name=Hello, token=aToken]\n"
				+ "secondPlayer = [name=You, token=anotherToken]\n"
				+ "board = thisBoard\n"
				+ "where aToken = new Token(aColor) and aColor = [red=20, green=20, blue=20]\n"
				+ "anotherToken = new Token(anotherColor) and anotherColor = [red=120, green=120, blue=120]\n"
				+ "We will test setBoard() method and then see printing the board the result.\n"
				+ "In order to give a sense to it, firstPlayer's token is added to the board in a position.\n" 
				+ "Testing Board.insert() and Board.printBoard(), used for this test, can be found in the Board testing class.\n" 
				+ "The principle is that, if this method works, methods applied after will work.\n");
		
		Board aNewBoard = new Board();
		try {
			aNewBoard.insert(aToken, 4);
		} catch (FullColumnException e) {
		}

		System.out.println("Expected:  |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null null null null null null |\r\n" + 
				"                     |null null null null gameComponents.Token@chars null null |");
		System.out.println("Testing...");
		testSetBoard(match, aNewBoard);

		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for inserting a token in the board.\n"
				+ "This test is not so useful here, because insertInBoard(...) method\n"
				+ "simply calls the underlying method of the Board class, that has been tested otherwhere.\n"
				+ "It can be called that test class in order to see if there are problems with this method too.");

	}
	
	public static void testGetFirstPlayer(Match match) {
		System.out.println("Actually is: " + match.getFirstPlayer().getName());
	}
	
	public static void testGetSecondPlayer(Match match) {
		System.out.println("Actually is: " + match.getSecondPlayer().getName());
	}

	public static void testGetBoard(Match match) {
		System.out.println("Actually is: ");
		match.getBoard().printBoard();
	}	
	
	public static void testGetTurn(Match match) {
		System.out.println("Actually is: " + match.getTurn());	
	}
	
	public static void testWhoPlaysFirst(Match match) {
		boolean answer = match.whoPlaysFirst();
		System.out.println("Actually is: " + answer);
	}
	
	public static void testGetIsFirstPlayerPlaying(Match match) {
		boolean answer = match.getIsFirstPlayerPlaying();
		System.out.println("Actually is: " + answer);
	}
	
	public static void testSetFirstPlayerPlaying(Match match, boolean firstPlayerTurn) {
		match.setFirstPlayerPlaying(firstPlayerTurn);
		System.out.println("Actually is: " + match.getIsFirstPlayerPlaying());	
	}

	public static void testSetFirstPlayer(Match match, Player player) {
		match.setFirstPlayer(player);
		System.out.println("Actually is: " + match.getFirstPlayer().getName());
	}
	
	public static void testSetSecondPlayer(Match match, Player player) {
		match.setSecondPlayer(player);
		System.out.println("Actually is: " + match.getSecondPlayer().getName());
	}
	
	public static void testSetBoard(Match match, Board gameBoard) {
		match.setBoard(gameBoard);
		System.out.println("Actually is: ");
		match.getBoard().printBoard();
	}
	
	public static void testSetTurn(Match match, int turn) {
		match.setTurn(turn);
		System.out.println("Actually is: " + match.getTurn());	
	}
}
