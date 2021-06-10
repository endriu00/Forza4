package gameComponents;

/*Token class*/
public class Token {
	
	/*Variable tokenColor represents the color of the token */
	private Color tokenColor;
	
	/* For compatibility with GUI*/
	private java.awt.Color tokenColorJava;
	
	/**
	 * Basic constructor for the class Token
	 * @param color represents the color of the token
	 */
	public Token(Color color) {
		tokenColor = color;
	}

	/**
	 * For compatibility with GUI
	 * @param tokenColorJava
	 */
	public Token(java.awt.Color tokenColorJava) {
		// TODO Auto-generated constructor stub
		this.tokenColorJava = tokenColorJava;
	}

	/**
	 * Basic getter method for tokenColor variable
	 * @return the color of the token
	 */
	public Color getTokenColor() {
		return tokenColor;
	}
}
