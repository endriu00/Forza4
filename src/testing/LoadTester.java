package testing;

import java.io.FileNotFoundException;

import customException.CorruptedFileException;
import gameComponents.Match;
import saveAndLoad.LoadMatch;

/**
 * Class for testing methods and behavior of the class LoadMatch, as this class name suggests.
 * This is fundamentally a unit testing.
 * In the class, it will be loaded a series of file representing saved matches.
 * The expected behavior of each loading will be presented during the execution of each test.
 * 
 * Talking about the structure of the class, each test has got its static method.
 * Each method is then invoked in the main.
 * 
 * @author andre
 */
public class LoadTester {

public static void main(String[] strings) {
		
		System.out.println("\n\nWELCOME TO THIS UNIT TEST!\n"
				+ "\n"
				+ "Here we will test the behavior of the methods of the class "
				+ "LoadMatch\n"
				+ "In the class, it has been written, for each test, the purpose of the test, so a short explanation, \n"
				+ "an expected result, and the effective result of the test.\nEasy to understand, if the expected result \n" 
				+ "and the result itself are the same, the test has been successful.\n\n");
		
		System.out.println("Let's begin!\n\n");
		
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV\n\n\n");
		
		System.out.println("Testing the loading of a properly created file.\n"
						 + "This was the file:\n"
						 + "First player: a, token color: 205 245 247\r\n" + 
						 "Second player: b, token color: 20 20 20\r\n" + 
						 "Turn: 15 \r\n" + 
						 "Board: \r\n" + 
						 "Row: 5 Col: 0 20 20 20\r\n" + 
						 "Row: 5 Col: 1 205 245 247\r\n" + 
						 "Row: 5 Col: 2 20 20 20\r\n" + 
						 "Row: 5 Col: 3 205 245 247\r\n" + 
						 "Row: 5 Col: 4 20 20 20\r\n" + 
						 "Row: 5 Col: 5 20 20 20\r\n" + 
						 "Row: 5 Col: 6 205 245 247\r\n" + 
						 "Row: 4 Col: 1 20 20 20\r\n" + 
						 "Row: 4 Col: 2 205 245 247\r\n" + 
						 "Row: 4 Col: 4 20 20 20\r\n" + 
						 "Row: 4 Col: 5 205 245 247\r\n" + 
						 "Row: 3 Col: 1 205 245 247\r\n" + 
						 "Row: 3 Col: 2 20 20 20\r\n" + 
						 "Row: 3 Col: 4 205 245 247\r\n" + 
						 "This file comes from a saved match, so it is a correctly created match.\n"
						 + "You can easily load this match from the GUI to see that it works.\n"
						 + "It is the file called loadMatchTest1.txt"
						 + "We will load it.\n"
						 + "Loading the file means that a new variable of type Match is created.\n"
						 + "In order to see if it has worked, there will be a series of comparisons\n"
						 + "between the match stored in the saved file pattern and the characteristics\n"
						 + "of the new crafted match.\n");
		
		System.out.println("Expected:\n"
				+ "first player name: a\n"
				+ "second player name: b\n"
				+ "what turn is it? 15\n"
				+ "first player's token color: 205 245 247");
		System.out.println("Testing...");
		testLoadMatch("loadMatchTest1");
		System.out.println("Test finished.\n");
		
		
		System.out.println("---------------------------------------------------------------------\n");

		System.out.println("Testing the loading of a corrupted file.\n"
				 + "This was the file:\n"
				 + "First player: a, token color: 205 245 247\r\n" + 
				 "Second player: b, token color: 20 20 20\r\n" + 
				 "Turn: 21 \r\n" + 
				 "Board: \r\n" + 
				 "Row: 5 Col: 0 20 20 20\r\n" + 
				 "Row: 5 Col: 1 205 245 247\r\n" + 
				 "Row: 5 Col: 2 20 20 20\r\n" + 
				 "Row: 5 Col: 3 205 245 247\r\n" + 
				 "Row: 5 Col: 4 20 20 20\r\n" + 
				 "Row: 5 Col: 5 20 20 20\r\n" + 
				 "Row: 5 Col: 6 205 245 247\r\n" + 
				 "Row: 4 Col: 1 20 20 20\r\n" + 
				 "Row: 4 Col: 2 205 245 247\r\n" + 
				 "Row: 4 Col: 4 20 20 20\r\n" + 
				 "Row: 4 Col: 5 205 245 247\r\n" + 
				 "Row: 3 Col: 1 205 245 247\r\n" + 
				 "Row: 3 Col: 2 20 20 20\r\n" + 
				 "Row: 3 Col: 4 205 245 247\r\n" + 
				 "This file had been hand tampered, so it is not a correctly created match.\n"
				 + ""
				 + "It is the file called loadMatchTest2.txt"
				 + "We will load it.\n"
				 + "Loading the file means that a new variable of type Match is created.\n"
				 + "The modified thing that makes the file corrupted is the number of turn,\n"
				 + "that does not respect the bound turn == numberOfTokens + 1.\n"
				 + "It is expected that a CorruptedFileException is thrown.\n");

		System.out.println("Expected: File trying to read is corrupted.");
		System.out.println("Testing...");
		String s = "loadMatchTest2";
		testLoadMatch(s);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Testing the loading of a non-existing file.\n"
				 + "It is a so-called loadNotFound.txt file"
				 + "\n"
				 + "It is expected that a FileNotFoundException is thrown.\n");

		System.out.println("Expected: File was not found!");
		System.out.println("Testing...");
		String s1 = "loadNotFound";
		testLoadMatch(s1);
		System.out.println("Test finished.\n");
	}

	public static void testLoadMatch(String loadingFile) {
		LoadMatch loader = new LoadMatch();
		try {
			Match loadedMatch = loader.loadMatch(loadingFile + ".txt");
			System.out.println("Actually is: \n"
					+ "first player name: " + loadedMatch.getFirstPlayer().getName() 
					+ "\nsecond player name: " + loadedMatch.getSecondPlayer().getName() 
					+ "\nwhat turn is it? " + loadedMatch.getTurn()
					+ "\nfirst player's token color: " + loadedMatch.getFirstPlayer().getToken().getTokenColor().getRed() + " "
					+ loadedMatch.getFirstPlayer().getToken().getTokenColor().getGreen() + " "
					+ loadedMatch.getFirstPlayer().getToken().getTokenColor().getBlue());
		} catch (FileNotFoundException e) {
			System.out.println("Actually is:");
			System.out.println("File was not found!");
		} catch (CorruptedFileException e) {
			System.out.println("Actually is:");
			System.out.println(e.getError());
		}
	}
}
