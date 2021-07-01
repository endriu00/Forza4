package gameComponents;

import customException.FullColumnException;

/**
 * Match class is the class representing the match of a game, so everything concerning who is actually playing, which turn is it,
 * the gameBoard and its state (so what tokens are in it), whose turn is it and so on.
 * Conceptually talking, this class is part of the gameComponents package, that represents where the logic of the application
 * lies in. This means that other classes interact with this package and, ideally, with this class only, being the direct 
 * frontier of the external view of the internal logic.
 * @author andre
 *
 */
public class Match {

	/** 
	 * Variable turn is the variable indicating the turn of the match. 
	 * According to the number of boxes in the gameboard, the last possible turn for as match is the 42nd:
	 * as stated in the Board class, the number of rows is 6, while the number of columns is 7; 
	 * if we calculate the product rows x columns, we obtain the number 42, that is the total amount of 
	 * fillable boxes in which players can insert the token. So, if neither of the players has won till the end 
	 * and the gameboard is full, they must have reached the 42nd turn of the match.
	 * 
	 * First turn is set to 1, in order to give user readability.
	 * */
	private int turn;
	
	/**
	 *  Variable gameboard is the gameboard of the match 
	 */
	private Board gameBoard;
	
	/**
	 * firstPlayer is the player ideally inserted first in the match. It does not represent the player playing first in the match.
	 */
	private Player firstPlayer;
	
	/**
	 * secondPlayer is the player ideally inserted second in the match. It does not represent the player playing second in the match.
	 */
	private Player secondPlayer;
	
	/**
	 *  Variable isFirstPlayerPlaying is true if the first player is playing, false otherwise.
	 *  This determines the correct succeeding of turns in the match.
	 */
	private boolean isFirstPlayerPlaying;
	
	/**
	 * Basic constructor for the Match class
	 * Note that variable turn is set to 1. So the match will begin with turn labeled to 1.
	 * In addition, it is created a new Board.
	 * @param p1 is the first player 
	 * @param p2 is the second player
	 */
	public Match(Player p1, Player p2) {
		this.gameBoard = new Board();
		firstPlayer = p1;
		secondPlayer = p2;
		turn = 1;
	}
	
	/**
	 * Basic constructor for the Match class
	 * Note that variable turn is set to 1. So the match will begin with turn labeled to 1.
	 * @param gameboard is the gameboard of the match
	 * @param p1 is the first player 
	 * @param p2 is the second player
	 */
	public Match(Board gameboard, Player p1, Player p2) {
		this.gameBoard = gameboard;
		this.firstPlayer = p1;
		this.secondPlayer = p2;
		this.turn = 1;
	}
			
	/**
	 * Method for determining which player plays first.
	 * This player could be first or second player.
	 * 
	 * @return isFirstPlayerPlaying true if firstPlayer plays first, false otherwise.
	 */
	public boolean whoPlaysFirst()
	{
		isFirstPlayerPlaying = Math.random() <= 0.5;
		return isFirstPlayerPlaying;
	}
	
	/**
	 * Method for inserting a token in a given column in the gameBoard of the match.
	 * It simply calls the insert method of the Board class to complete the mediation between classes.
	 * @param token is the token to be inserted.
	 * @param column is the column where the token has to be inserted.
	 * @throws FullColumnException exception of a full column.
	 */
	public void insertInBoard(Token token, int column) throws FullColumnException {
		this.gameBoard.insert(token, column);
	}
	
	/*From here it starts a series of useful method intended to be used in other classes*/
	
	/**
	 * Method for getting isFirstPlayerPlaying.
	 * @return isFirstPlayerPlaying variable value.
	 */
	public boolean getIsFirstPlayerPlaying() {
		return this.isFirstPlayerPlaying;
	}
	
	/**
	 * Method that returns the first player of the match.
	 * @return the first player.
	 */
	public Player getFirstPlayer() {
		return this.firstPlayer;
	}
	
	/**
	 * Method that returns the second player of the match.
	 * @return the second player.
	 */
	public Player getSecondPlayer() {
		return this.secondPlayer;
	}
	
	/**
	 * Method that returns the gameboard of the match.
	 * @return the gameboard.
	 */
	public Board getBoard() {
		return this.gameBoard;
	}	
	
	/**
	 * Method that returns the turn of the match being played.
	 * @return turn the current turn of the match.
	 */
	public int getTurn() {
		return turn;
	}
	
	/**
	 * Method for setting isFirstPlayerPlaying variable.
	 * @param firstPlayerPlaying is true if first player is playing.
	 */
	public void setFirstPlayerPlaying(boolean firstPlayerPlaying) {
		this.isFirstPlayerPlaying = firstPlayerPlaying;
	}
	
	/**
	 * Method for setting Player firstPlayer. 
	 * @param firstPlayer is a player of the match, namely the first being inserted. This is not necessarily the player beginning the match.
	 */
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	/**
	 * Method for setting Player secondPlayer.
	 * @param secondPlayer is a player of the match, namely the second being inserted. 
	 * This is not necessarily the player making his move after the first player, but could be the player beginning the match.
	 */
	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}
	
	/**
	 * Method for setting the Board of the match.
	 * @param gameBoard is the Board object to be set.
	 */
	public void setBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	/**
	 * Method for setting the turn of the match.
	 * @param turn is the turn to be set.
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
}

