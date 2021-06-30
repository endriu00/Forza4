package gameComponents;

import java.awt.Color;

/**
 * Class representing the Token, defined as the element the player can interact with, so the element determining players' "moves".
 * This class presents getters and setters for token only variable: its color.
 * 
 * Note: The class Color was chosen for giving the token a color above a custom, own class because of its compatibility with Swing framework.
 * @author andre
 *
 */
public class Token {
	
	/*Variable tokenColor represents the color of the token */
	private Color tokenColor;
	
	/**
	 * Basic constructor for the class Token
	 * @param color represents the color of the token
	 */
	public Token(Color color) {
		tokenColor = color;
	}

	/**
	 * Basic getter method for tokenColor variable
	 * @return the color of the token
	 */
	public Color getTokenColor() {
		return tokenColor;
	}
	
	/**
	 * Basic setter method for tokenColor variable
	 * @param color is the color to be set
	 */
	public void setTokenColor(Color color) {
		tokenColor = color;
	}	
}
