package playGame;

import gameComponents.*;

public class ConnectFour {
	
	public static void main(String[] args) 
	{
			Board gb = new Board();
			String name1 = "Ciccio";
			Color color1 = new Color(255, 0, 0);
			Token token1 = new Token(color1);
			Player p1 = new Player(name1, token1);
			
			String name2 = "Bugo";
			Color color2 = new Color(0, 255, 0);
			Token token2 = new Token(color2);
			Player p2 = new Player(name2, token2);
			
			
			Match match = new Match(gb, p1, p2);
			
			match.executeTurn();
			
			Board.printBoard();
	}
			
	

}
