package gameComponents;

public class Board 
{
	private static String[][] gameBoard;
	private static final int ROWS = 7;
	private static final int COLUMNS = 6;
	
	/**
	 * Costruisce una griglia vuota.
	 */
	public class Board 
	{
	private static String[][] gameBoard;
	private static final int ROWS = 7;
	private static final int COLUMNS = 6;
	
	/**
	 * Costruisce una griglia vuota.
	 */
	public Board()
	{
		gameBoard = new String[ROWS][COLUMNS];
		
	}
	
	/**
	 * Stampa la rappresentazione della griglia allo stato iniziale, prima dell'inizio del primo turno.
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
	
	
	
	/*TO DO
	/**
	 * Controlla se la mossa è consentita, ossia se la colonna non è vuota e se la griglia non è piena.
	 * @return true se la mossa è consentita.
	 */
	/*TO DO
	public boolean isAllowed()
	{
		 if (isColumnFull() || isBoardFull(String[][] gameBoard))
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
		int i = 0;
		int j = 0;
		for (i = 0; i < ROWS; i++)
			for (j = 0; j < COLUMNS; i++)
				if (!gameBoard.equals(" "))
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
		
	}*/
}
