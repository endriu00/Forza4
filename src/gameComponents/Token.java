package gameComponents;

/*Token class*/
public class Token {
	
	/*Variable tokenColor represents the color of the token */
	private Color tokenColor;
	
	/*Variable tokenRadius represents the radius of the token. Its dimensions are set, so it is labeled with final keyword */
	private final int tokenRadius = 1;
	
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
	 * Basic getter method for tokenRadius variable
	 * @return the radius of the token
	 */
	public int getTokenRadius() {
		return tokenRadius;
	}
	
	/**
	 * Basic setter method for tokenColor variable
	 * @param tokenColor is the color of the token
	 */
	public void setTokenColor(Color tokenColor) {
		this.tokenColor = tokenColor;
	}
	

}
