package gameComponents;

import customException.ColumnOutOfRange;
import customException.FullColumnException;

/**
 * Board class is the class creating the board and managing it at a logic level, meaning regulating the insertion of a token in the gameBoard with proper controls,
 * creating the board itself, controlling if a series of token placed in a correct way determine a win or a draw in the overlying match and providing methods
 * particularly useful in other classes accessing them.
 *
 */
public class Board 
{
	/**
	 * gameBoard is a matrix of token, so a Token object can be inserted in it. 
	 * This allows controls to be made over tokens.
	 */
	private static Token[][] gameBoard;
	
	/**
	 * Number of rows of the matrix. This number is defined in the domain of the problem.
	 */
	private static final int ROWS = 6;
	
	/**
	 * Number of columns of the matrix. This number is defined in the domain of the problem.
	 */
	private static final int COLUMNS = 7;
	
	/**
	 * Constructs the board.
	 */
	public Board()
	{
		gameBoard = new Token[ROWS][COLUMNS];	
	}
	
	/**
	 * Prints the board in the standard output of the machine.
	 * Only used for unit testing and debugging purpose.
	 */
	public void printBoard()
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
	 * @throws FullColumnException if the column trying to insert the token in is full.
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
	public boolean isColumnFull(int column) {
		if(gameBoard[0][column] == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Controls if the board is full.
	 * @return true if the board is full, false otherwise.
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
	 * Controls if the match ended in a draw.
	 * @return true if the match ended in a draw, 
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
	 * Controls if the match ended in a win.
	 * @return true if someone has won the match.
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
	 * Checks if there is a win determined by placing four tokens in a horizontal fashion.
	 * @return true if there is an horizontal win, false otherwise.
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
	 * Checks if there is a win determined by placing four tokens in a vertical fashion.
	 * @return true if there is a vertical win, false otherwise.
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
	 * Checks if there is a win determined by placing four tokens in a diagonal fashion, from bottom upward to the right.
	 * @return true if there is a bottom-right diagonal win, false otherwise.
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
	 * Checks if there is a win determined by placing four tokens in a diagonal fashion, from bottom upward to the left.
	 * @return true if there is a bottom-left diagonal win, false otherwise.
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
	 * @return the number of columns of the board
	 */
	public static int getNumberOfColumns() {
		return COLUMNS;
	}
}
