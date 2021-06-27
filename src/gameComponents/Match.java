package gameComponents;

import java.util.Scanner;

import customException.FullColumnException;
import saveAndLoad.SaveMatch;

/*Match class*/
public class Match {

	/* Variable turn is the variable indicating the turn of the match. 
	 * According to the number of boxes in the gameboard, the last possible turn for as match is the 42nd:
	 * as stated in the Gameboard class, the number of rows is 6, while the number of columns is 7; 
	 * if we calculate the product rows x columns, we obtain the number 42, that is the total amount of 
	 * fillable boxes in which players can insert the token. So, if neither of the players has won till the end 
	 * and the gameboard is full, they must have reached the 42nd turn of the match.
	 * 
	 * First turn is set to 1, in order to give readability.
	 * */
	private int turn;
	
	/* Variable gameboard is the gameboard of the match */
	private Board gameBoard;
	
	
	/* Variable firstPlayer is the first player of the match, the one who begins inserting token. 
	 * firstPlayer will play during odd turns (he begins the match at turn = 1, he will play again at turn = 3, and so on). */
	private Player firstPlayer;
	
	/* Variable secondPlayer is the second player of the match. 
	 * secondPlayer will play during even turns (he begins the match at turn = 2 and will play again at turn = 4, ...). */
	private Player secondPlayer;
	
	/* Variable firstPlayerTurn is true if the first player plays first.*/
	private boolean firstPlayerTurn;
	
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
	
	public int getTurn() {
		return turn;
	}
			
	/**
	 * Metodo che stabilisce chi tra i due giocatori esegue il turno.
	 * @return firstPlayerTurn true se ha il turno, false altrimenti.
	 */
	public boolean whoPlaysFirst()
	{
		firstPlayerTurn = Math.random() <= .5;
		return firstPlayerTurn;
	}
	
	/**
	 * Metodo che stabilisce se la partita è finita.
	 * @param gameBoard
	 * @return true se la partita è finita.
	 */
	public boolean isEndGame(Board gameBoard)
	{
		if (gameBoard.isBoardFull() || gameBoard.isDraw() || gameBoard.isWin())
		{
			return true;
		}
		return false;
	}
	
	public void insertInBoard(Token token, int column) throws FullColumnException {
		this.gameBoard.insert(token, column);
	}
	
	/**
	 * Method used for save game and quit.
	 * 1. Integrate with GUI
	 * 2. Change method to ask player the name of the match to be saved (this is part of the integration)
	 * 3. Provide res directory
	 */
	public void saveGameAndQuit() {
		SaveMatch save = new SaveMatch("Partita1", this);
		
	}
	
	/*From here it starts a series of useful method intended to be used in other classes*/
	
	/**
	 * Method for getting firstPlayerTurn 
	 * @return firstPlayerTurn variable value
	 */
	public boolean getFirstPlayerTurn() {
		return this.firstPlayerTurn;
	}
	
	/**
	 * Method that returns the first player of the match
	 * @return the first player
	 */
	public Player getFirstPlayer() {
		return this.firstPlayer;
	}
	
	/**
	 * Method that returns the second player of the match
	 * @return the second player
	 */
	public Player getSecondPlayer() {
		return this.secondPlayer;
	}
	
	/**
	 * Method that returns the gameboard of the match
	 * @return the gameboard
	 */
	public Board getBoard() {
		return this.gameBoard;
	}
	
	public void setFirstPlayerTurn(boolean firstPlayerTurn) {
		this.firstPlayerTurn = firstPlayerTurn;
	}
	
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}
	
	public void setBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
}

