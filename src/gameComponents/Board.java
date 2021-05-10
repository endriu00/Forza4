package gameComponents;

public class Board {
	
	private String[][] gameBoard;
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	
	/**
	 * Costruisce una griglia vuota.
	 */
	public Board()
	{
		gameBoard = new String[ROWS][COLUMNS];
		
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; j++)
				gameBoard[i][j] = " ";
	}
	
	/**
	 * Controlla se la mossa è consentita, ossia se la colonna non è vuota e se la griglia non è piena.
	 * @return true se la mossa è consentita.
	 */
	public boolean isAllowed()
	{
		 if (isColumnFull() || isBoardFull())
		    return false;
		 return true;
	}
	
	public boolean isColumnFull()
	{
		for (int j = 0; j < COLUMNS; j++)
			if (j == 0)
				return false;
		return true;
	}
	
	public boolean isBoardFull(String[][] gameBoard)
	{
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; i++)
				if (!gameBoard[i][j].equals(" "))
					return true;
		return false;
	}
	
	
	public boolean isDraw()
	{
		if (isBoardFull() && !isWin())
			return true;
	}
	
	public boolean isWin()
	{
		if (isVerticalWin() || isHorizontalWin() 
			||isBottomRighttDiagonalWin() || isBottomLeftDiagonalWin())
			return true;
		return false;
	}
	
	public boolean isVerticalWin()
	{
		
	}
	
	public boolean isHorizontalWin()
	{
		
	}
	
	public boolean isBottomRighttDiagonalWin()
	{
		
	}
	
	public boolean isBottomLeftDiagonalWin()
	{
		
	}
}
