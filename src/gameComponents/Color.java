package gameComponents;

/*Color class
 *The color refers to token color. */
public class Color {
	
	private int red;
	private int green;
	private int blue;
	
	/**
	 * Basic constructor for Color class
	 * The color is made of three components: [red, green, blue]. 
	 * Their value can be at most 255, because each component is represented by a byte in memory.
	 * 
	 * @param red is the red component of the color 
	 * @param green is the green component of the color
	 * @param blue is the blue component of the color
	 */
	public Color(int red, int green, int blue) {
		//Add control for inserted value over 255??
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

}
