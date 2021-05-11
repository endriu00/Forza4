package gameComponents;

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
	 * Controlla se la mossa è consentita, ossia se la colonna non è vuota e se la griglia non è piena.
	 * @return true se la mossa è consentita.
	 */
	public boolean isAllowed()
	{
		 if (isColumnFull() || isBoardFull())
		    return false;
		 return true;
	}
	
	/**
	 * Controlla se la colonna è piena.
	 * @return true se la colonna è piena.
	 */
	public boolean isColumnFull()
	{
		for (int j = 0; j < COLUMNS; j++)
			if (!(gameBoard[ROWS][j] == null))
				return false;
		return true;
	}
	
	/**
	 * Controlla se la griglia è piena.
	 * @return true se la griglia è piena.
	 */
	public boolean isBoardFull()
	{
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; i++)
				if ((gameBoard[i][j] == null))
					return false;
		return true;
	}
	
	/**
	 * Controlla se la partita si è conclusa con un pareggio.
	 * @return true se se la partita si è conclusa con un pareggio.
	 */
	public boolean isDraw()
	{
		if (isBoardFull() && !isWin())
			return true;
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
			return true;
		return false;
	}
	
	/**
	 * Controlla se c'è una vincita data da quattro token dello stesso colore posizionati in
	 * posizioni contigue per verticale.
	 * @return true se c'è una vincita verticale.
	 */
	public boolean isVerticalWin()
	{
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; i++)
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i][j+1]) && (gameBoard[i][j].equals(gameBoard[i][j+2])
						&& (gameBoard[i][j].equals(gameBoard[i][j+3])))))
				{
					return true;
				}
		return false;					
	}
	
	/**
	 * Controlla se c'è una vincita data da quattro token dello stesso colore posizionati in
	 * posizioni contigue per orizzontale.
	 * @return true se c'è una vincita orizzontale.
	 */
	public boolean isHorizontalWin()
	{
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; i++)
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i+1][j]) && (gameBoard[i][j].equals(gameBoard[i+2][j])
						&& (gameBoard[i][j].equals(gameBoard[i+3][j])))))
				{
					return true;
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
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; i++)
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i-1][j-1]) && (gameBoard[i][j].equals(gameBoard[i-2][j-2])
						&& (gameBoard[i][j].equals(gameBoard[i-3][j-3])))))
				{
					return true;
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
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; i++)
				if (gameBoard[i][j] != null && (gameBoard[i][j].equals(gameBoard[i+1][j+1]) && (gameBoard[i][j].equals(gameBoard[i+2][j+2])
						&& (gameBoard[i][j].equals(gameBoard[i+3][j+3])))))
				{
					return true;
				}
		return false;
	}
}

