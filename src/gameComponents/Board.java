package gameComponents;

import customException.ColumnOutOfRange;
import customException.FullColumnException;
/**
 * Griglia 7x6, matrice di Token.
 */
public class Board 
{
	private static Token[][] gameBoard;
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	
	/**
	 * Costruisce una griglia vuota.
	 */
	public Board()
	{
		gameBoard = new Token[ROWS][COLUMNS];	
	}
	
	/**
	 * Stampa la griglia allo stato iniziale.
	 */
	public static void printBoard()
	{
		System.out.println("\n");
		for (int i = 0; i < ROWS; i++)
		{
			System.out.print("                     |");
			for (int j = 0; j < COLUMNS; j++)
			{
				System.out.print(gameBoard[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("\n");
	}
	
	/**
	 * Method for inserting a token in a given column
	 * In order to control if the move is allowed, it controls if the column where the player wants to insert
	 * the token in is full or not.
	 * Once verified, the method descends the gameBoard (say, the matrix) everytime there is not a token in the cell below.
	 * Code talking, there is a variable, tempRow, set to 0, that is the first row of the matrix.
	 * If the cell below, tempRow+1, has got no token inside of it (if the object stored inside of it is still null),
	 * we can descend and continue this verification cycle.
	 * Once found a token in the cell below, the cycle stops, and tempRow has stored inside of it the value 
	 * of the last empty cell discovered during the descending.
	 * The token is then inserted into the position tempRow-column - so above the last token in the given column.
	 * 
	 * Note that there is a special case: the first insert into an empty column. 
	 * We cannot descend the matrix in this case, because this would lead tempRow+1 to be bigger than the actual number of rows.
	 * In that case, the first if is instantly verified, the token is inserted and the method returns.
	 * 
	 * Note also that it is not necessary to provide the row, because the game must simulate the reality, 
	 * so the token will be positioned in the lower level row not occupied by other tokens, not being able to 
	 * intersect it, nor being able to fly above an empty cell, because of the gravity force.
	 * 
	 * @param token is the token to be inserted in the gameBoard
	 * @param column is the column in which the player wants to insert the token in
	 * @throws FullColumnException 
	 */
	
	public void insert(Token token, int column) throws FullColumnException {
			
		try {
			if(column < 0 || column > 6) throw new ColumnOutOfRange();
		}
		catch(ColumnOutOfRange e) {
			return;
		}
		
		
		if(isColumnFull(column)) {
			throw new FullColumnException();
		}
		
		

		if(gameBoard[ROWS-1][column] == null) 	
		{
			gameBoard[ROWS-1][column] = token;
			return;	
		}	
		
		int tempRow = 0;
		if(!isColumnFull(column)) {
			while(gameBoard[tempRow+1][column] == null) {
			tempRow++;
			}
		}
		gameBoard[tempRow][column] = token;
	} 
	
	/**
	 * Controlla se la mossa è consentita, ossia se la colonna non è vuota e se la griglia non è piena.
	 * @return true se la mossa è consentita.
	 */
	/*public boolean isAllowed()                    // Non dovrebbe servire
	{
		 if (isColumnFull() || isBoardFull())
		    return false;
		 return true;
	}*/
	
	
	/**
	 * Method for determining if a given column is full.
	 * To achieve this purpose, it sees the gameBoard in position row = 0, column = column:
	 * if this position has no object inside (e.g. is null), the column is not full, because 
	 * there is still space for at least one more object, so it returns false to the artificial 
	 * question "is column full";
	 * otherwise, if there is an object in that position, so if there is a non-null object,
	 * returns true: the column is full.
	 * @param column is the column to be checked
	 * @return true if the column is full, false if the column has still got a space for another token.
	 */
	public boolean isColumnFull(int column)
	{
		if(gameBoard[0][column] == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Controlla se la griglia è piena.
	 * @return true se la griglia è piena.
	 */
	public boolean isBoardFull()
	{
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLUMNS; j++)
			{
				if (gameBoard[i][j] == null)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Controlla se la partita si è conclusa con un pareggio.
	 * @return true se se la partita si è conclusa con un pareggio.
	 */
	public boolean isDraw()
	{
		if (isBoardFull() && !isWin())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Controlla se la partita è stata vinta da uno dei due giocatori.
	 * @return true se la partita è stata vinta.
	 */
	public boolean isWin()
	{
		if (isVerticalWin() || isHorizontalWin() 
			||isBottomRightDiagonalWin() || isBottomLeftDiagonalWin())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Controlla se c'è una vincita data da quattro token dello stesso colore posizionati in
	 * posizioni contigue per verticale.
	 * @return true se c'è una vincita verticale.
	 */
	public boolean isHorizontalWin()  
	{
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLUMNS-3; j++)
			{
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i][j+1]) && (gameBoard[i][j].equals(gameBoard[i][j+2])
						&& (gameBoard[i][j].equals(gameBoard[i][j+3])))))
				{
					return true;
				}
			}
		}
		return false;					
	}
	
	/**
	 * Controlla se c'è una vincita data da quattro token dello stesso colore posizionati in
	 * posizioni contigue per orizzontale.
	 * @return true se c'è una vincita orizzontale.
	 */
	public boolean isVerticalWin()
	{
		for (int i = 0; i < ROWS - 3; i++)
		{
			for (int j = 0; j < COLUMNS; j++)
			{
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i+1][j]) && (gameBoard[i][j].equals(gameBoard[i+2][j])
						&& (gameBoard[i][j].equals(gameBoard[i+3][j])))))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Controlla se c'è una vincita data da quattro token dello stesso colore posizionati in
	 * posizioni contigue per diagonale da destra in basso.
	 * @return true se c'è una vincita diagonale.
	 */
	public boolean isBottomRightDiagonalWin()
	{
		for (int i = 0; i < ROWS - 3; i++)
		{
			for (int j = 3; j < COLUMNS; j++)
			{
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i+1][j-1]) && (gameBoard[i][j].equals(gameBoard[i+2][j-2])
						&& (gameBoard[i][j].equals(gameBoard[i+3][j-3])))))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Controlla se c'è una vincita data da quattro token dello stesso colore posizionati in
	 * posizioni contigue per diagonale da sinistra in basso.
	 * @return true se c'è una vincita diagonale.
	 */
	public boolean isBottomLeftDiagonalWin()
	{
		for (int i = 0; i < ROWS - 3; i++)
		{
			for (int j = 0; j < COLUMNS - 3; j++)
			{
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i+1][j+1]) && (gameBoard[i][j].equals(gameBoard[i+2][j+2])
						&& (gameBoard[i][j].equals(gameBoard[i+3][j+3])))))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/*From here it starts a series of useful methods to be used in other classes*/
	
	/**
	 * Method that returns the token in position i, j of the matrix
	 * @param i indicates the row variable
	 * @param j indicates the column variable
	 * @return the token in position i, j
	 */
	public Token getTokenInPosition(int i, int j) {
		return gameBoard[i][j];
	}
	
	/**
	 * Method for getting the number of rows. 
	 * Although the number of rows is indicated in the description and in the domain of
	 * the project, this method is provided in order to make order and depend exclusively 
	 * on this class to know the number of rows 
	 * @return the number of rows of the board
	 */
	public static int getNumberOfRows() {
		return ROWS;
	}
	
	/**
	 *  Method for getting the number of columns. 
	 * Although the number of columns is indicated in the description and in the domain of
	 * the project, this method is provided in order to make order and depend exclusively 
	 * on this class to know the number of columns 
	 * @return
	 */
	public static int getNumberOfColumns() {
		return COLUMNS;
	}
}
