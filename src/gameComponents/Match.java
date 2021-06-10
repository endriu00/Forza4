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
	 * @param p1 is the first player (e.g. the player who will start the game, who has the first turn)
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
	 * @param p1 is the first player (e.g. the player who will start the game, who has the first turn)
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
	
	public void grantPlayerMove(Player player) throws FullColumnException  {
		//int col = GUI Class asks player where does he want to insert the token or lets the user choose with mouse - this one better
		//player.insertToken(, gameboard);
		int inputCol;
		Scanner in = new Scanner(System.in);
		System.out.println("Player " + player.getName() + ", please insert the column in which you want to insert your token!\n"
				+ "Columns are numbered from 0 to 6.");
		inputCol = in.nextInt();
		if (gameBoard.isColumnFull(inputCol))
		{
			do
			{
			System.out.println("The column is full! Please try inserting the token in another allowed, not-full column!");
			inputCol = in.nextInt();
			}
			while (gameBoard.isColumnFull(inputCol));
			gameBoard.insert(player.getToken(), inputCol);
		}
		else
		{
		gameBoard.insert(player.getToken(), inputCol);	
		}
	}
	
	
	
	/**
	 * Metodo che fa eseguire il turno.
	 * @throws FullColumnException 
	 */
	public void executeTurn() throws FullColumnException
	{
	    whoPlaysFirst(); 
	    Board.printBoard();
	    Scanner inSave = new Scanner(System.in);
	    for ( ; turn <= 42; turn++)    
	    {
	    	System.out.println("Do you want to save the match? If yes, type save\n"
	    			+ "Otherwise, type no\n");
	    	
	    	if(inSave.next().equals("save")) {
	    		saveGameAndQuit();
	    		inSave.close();
	    		break;
	    	}
	    	
	    	
	    	if (!isEndGame(gameBoard))
	    	{
				if (firstPlayerTurn) 
		        {
		        	grantPlayerMove(firstPlayer);
		        } else {
		            grantPlayerMove(secondPlayer);
		        }
		        firstPlayerTurn = !firstPlayerTurn;
		        Board.printBoard();
	    	}
	    }	    	    
	}
	
	/**
	 * Metodo che stabilisce chi tra i due giocatori esegue il turno.
	 * @return firstPlayerTurn true se ha il turno, false altrimenti.
	 */
	public boolean whoPlaysFirst()
	{
		return firstPlayerTurn = Math.random() <= .5;
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

