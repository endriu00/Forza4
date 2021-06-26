package gameComponents;

import java.awt.Color;

/*Token class*/
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
	
}
