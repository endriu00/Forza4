package gameComponents;

/**
 * Class that represents a player. This is the actor of the game, because it represents the user, with two different characteristics:
 * a name and a token. This class presents useful methods for getting and setting these characteristics.
 * @author andre
 */
public class Player {

	/*Variable that represents the name of the player */
	private String name;
	
	/*Variable that represents the token used by the player */
	private Token token;
	
	/**
	 * Constructor
	 * @param name indicates the name of the player
	 * @param token indicates the token used by the player
	 */
	public Player(String name, Token token) {
		this.name = name;
		this.token = token;
	}
	
	/**
	 * Basic getter method for the name (of the player)
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Basic getter method for token field
	 * @return the token of the player
	 */
	public Token getToken() {
		return this.token;
	}
	
	/**
	 * Basic setter method for name field
	 * @param name is the name of the player to be assigned
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Basic setter method for token field
	 * @param token is the token to be assigned
	 */
	public void setToken(Token token) {
		this.token = token;
	}
	
}
