package testing;

import gameComponents.Color;

/**
 * Class for testing methods and behavior of the class Color, as this class name suggests.
 * This is fundamentally a unit testing.
 * In the class, it has been written, for each test, the purpose of the test, so a short explanation, 
 * an expected result, and the effective result of the test. Easy to understand, if the expected result
 * and the result itself are the same, the test has been successful.
 * In order to view these tests, you are provided a main method that will show you the testing results 
 * in the standard output view. To not harden the view, it has been provided a catchy view.
 * 
 * Talking about the structure of the class, each test has got its static method.
 * Each method is then invoked in the main.
 */
public class ColorTester {
	
	public static void main(String... strings) {
		
		int redComponent = 100;
		int greenComponent = 200;
		int blueComponent = 255;
		Color color = new Color(redComponent, greenComponent, blueComponent);
		
		System.out.println("Test for getting the red component of the color, so for testing getRed().");
		System.out.println("This method returns an int, that is the red component of the color encoded as an int.\n"
				+ "Expected red: 100");
		System.out.println("Testing...");
		testGetRed(color);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
		
		System.out.println("Test for getting the green component of the color, so for testing getGreen().");
		System.out.println("This method returns an int, that is the green component of the color encoded as an int.\n"
				+ "Expected green: 200");
		System.out.println("Testing...");
		testGetGreen(color);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
		
		System.out.println("Test for getting the blue component of the color, so for testing getBlue().");
		System.out.println("This method returns an int, that is the blue component of the color encoded as an int.\n"
				+ "Expected blue: 255");
		System.out.println("Testing...");
		testGetBlue(color);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
	} 
	
	public static void testGetRed(Color color) {
		System.out.println("Actually red is: " + color.getRed());
	}
	
	public static void testGetGreen(Color color) {
		System.out.println("Actually green is: " + color.getGreen());
	}
	
	public static void testGetBlue(Color color) {
		System.out.println("Actually blue is: " + color.getBlue());
	}

}
