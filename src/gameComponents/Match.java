package gameComponents;

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
	
	/**
	 * Basic constructor for the Match class
	 * Note that variable turn is set to 1. So the match will begin with turn labeled to 1.
	 * @param gameboard is the gameboard of the match
	 * @param p1 is the first player (e.g. the player who will start the game, who has the first turn)
	 * @param p2 is the second player
	 */
	public Match(Board gameboard, Player p1, Player p2) {
		this.gameBoard = new Board();
		firstPlayer = p1;
		secondPlayer = p2;
		turn = 1;
	}
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public void grantPlayerMove(Player player) {
		//int col = GUI Class asks player where does he want to insert the token or lets the user choose with mouse - this one better
		//player.insertToken(, gameboard);
	}
	
	
	/**
	 * Metodo che fa eseguire il turno.
	 */
	public void executeTurn()
	{
		boolean FirstPlayerTurn = false;
	    whoPlays(); 
	    Board.printBoard();
	    for ( ; turn <= 42; turn++)
	    {
	    	if (!(gameBoard.isWin()) || !(gameBoard.isDraw()))
	    	{
				if (FirstPlayerTurn) 
		        {
		        	grantPlayerMove(firstPlayer);
		        } else {
		            grantPlayerMove(secondPlayer);
		        }
		        FirstPlayerTurn = !FirstPlayerTurn;
		        Board.printBoard();
		        turn++;
	    	}
	    }	    	    
	}
	
	/**
	 * Metodo che stabilisce chi tra i due giocatori esegue il turno.
	 * @return
	 */
	public boolean whoPlays()
	{
		boolean FirstPlayerTurn = Math.random() <= .5;
		return FirstPlayerTurn; 
	}
}
