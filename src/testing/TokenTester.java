package testing;

import java.awt.Color;
import gameComponents.Token;

/**
 * Class for testing methods and behavior of the class Token, as this class name suggests.
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
public class TokenTester {

	public static void main(String... strings) {
		
		System.out.println("\n\nWELCOME TO THIS UNIT TEST!\n"
				+ "\n"
				+ "Here we will test the behavior of the methods of the class "
				+ "Token\n"
				+ "In the class, it has been written, for each test, the purpose of the test, so a short explanation, \n"
				+ "an expected result, and the effective result of the test.\nEasy to understand, if the expected result \n" 
				+ "and the result itself are the same, the test has been successful.\n\n");
		
		System.out.println("Let's begin!\n\n");
		
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV\n\n\n");
		
		Token token = new Token(new Color(144, 144, 144));
		
		System.out.println("Test for getting the token color, so for testing getTokenColor().");
		System.out.println("This method should return the token color, that is actually an object with an address"
				+ ", \nso it will be shown each component of the color (e.g. red, green, blue in the common format).\n\n"
				+ "Expected: \n"
				+ "Red component: 144\n"
				+ "Green component: 144\n"
				+ "Blue component: 144");
		System.out.println("Testing...");
		testGetTokenColor(token);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
		
	}
	
	public static void testGetTokenColor(Token token) {
		System.out.println("Actually is: \n"
				+ "Red component: " + token.getTokenColor().getRed() + "\nGreen component: " + token.getTokenColor().getGreen() + "\nBlue component: " + token.getTokenColor().getBlue());
	}
}
