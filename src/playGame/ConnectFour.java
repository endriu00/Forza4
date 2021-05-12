package playGame;

import gameComponents.*;

public class ConnectFour {
	
	public static void main(String[] args) 
	{
			Board gb = new Board();
			Scanner in = new Scanner(System.in);
			System.out.print(" Please enter your name: ");
			String name1 = in.next();
			Color color1 = new Color(255, 0, 0);
			Token token1 = new Token(color1);
			Player p1 = new Player(name1, token1);
			
			System.out.print(" Please enter your name: ");
			String name2 = in.next();
			Color color2 = new Color(0, 255, 0);
			Token token2 = new Token(color2);
			Player p2 = new Player(name2, token2);
			
			Match match = new Match(gb, p1, p2);
			
			match.executeTurn();
			
			Board.printBoard();
		
			if (gb.isDraw())    // Se è finita con un pareggio
			{
			    	System.out.print(" The match ended in a draw.");
			}
			else if (gb.isWin())    // Se è finita con una vincita
		    {
		    	System.out.print(" The match ended with a win.");
		    }	
	}
}
