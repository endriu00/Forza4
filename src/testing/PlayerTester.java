package testing;

import java.awt.Color;
import gameComponents.Player;
import gameComponents.Token;

/**
 * Class for testing methods and behavior of the class Player, as this class name suggests.
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
public class PlayerTester {
	
	public static void main(String... strings) {
		
		System.out.println("\n\nWELCOME TO THIS UNIT TEST!\n"
				+ "\n"
				+ "Here we will test the behavior of the methods of the class "
				+ "Player\n"
				+ "In the class, it has been written, for each test, the purpose of the test, so a short explanation, \n"
				+ "an expected result, and the effective result of the test.\nEasy to understand, if the expected result \n" 
				+ "and the result itself are the same, the test has been successful.\n\n");
		
		System.out.println("Let's begin!\n\n");
		
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV\n\n\n");
		
		String name = "andrea";
		Token token = new Token(new Color(23, 23, 23));
		Player player = new Player(name, token);
		
		System.out.println("Test for getting the player's name, so for testing getName().");
		System.out.println("This method returns a String.\n\n"
				+ "Expected: andrea");
		System.out.println("Testing...");
		testGetName(player);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
		
		System.out.println("Test for setting the player's name, so for testing setName(String aName).");
		System.out.println("We will set a new player's name, erica, and then show it with getName() method, previously tested.\n\n"
				+ "Expected: erica");
		System.out.println("Testing...");
		testSetName(player);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
		
		System.out.println("Test for getting the player's token, so for testing getToken().");
		System.out.println("This method returns a Token object.\n\n"
				+ "Expected: gameComponents.Token@anAddress");
		System.out.println("Testing...");
		testGetToken(player);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
		
		System.out.println("Test for setting the player's token, so for testing setToken(Token otherToken).");
		System.out.println("This method returns a Token object. In order to distinguish clearly the two objects,\n"
				+ "and so to know if the method has given its results, we will show the new token color and the old one.\n"
				+ "Previously the color was: 23, 23, 23\n\n"
				+ "Expected: 55, 55, 55");
		System.out.println("Testing...");
		testSetToken(player);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
	}
	
	public static void testGetName(Player player) {
		System.out.println("Actually is: " + player.getName());
	}
	
	public static void testSetName(Player player) {
		player.setName("erica");
		System.out.println("Actually is: " + player.getName());
	}

	public static void testGetToken(Player player) {
		System.out.println("Actually is: " + player.getToken());
	}
	public static void testSetToken(Player player) {
		System.out.println("Previously was: " + player.getToken().getTokenColor().getRed() + " " + player.getToken().getTokenColor().getGreen() + " " + player.getToken().getTokenColor().getBlue());
		Token otherToken = new Token(new Color(55, 55, 55));
		player.setToken(otherToken);
		System.out.println("Actually is: " + player.getToken().getTokenColor().getRed() + " " + player.getToken().getTokenColor().getGreen() + " " + player.getToken().getTokenColor().getBlue());
	}
}
