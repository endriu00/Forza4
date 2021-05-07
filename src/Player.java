/*Class that represents a player */
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
	 * Method that lets the player insert his token inside the board
	 * @param column is the column the player wants to insert the token in
	 * @param gameboard is the board in which the player will insert his token
	 */
	public void insertToken(int column, Board gameboard) {
		gameboard.insert(token, column); 
		
		
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
