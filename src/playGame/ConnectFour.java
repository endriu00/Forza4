package playGame;

import java.io.IOException;
import java.util.Scanner;

import customException.FullColumnException;
import gameComponents.*;
import saveAndLoad.LoadMatch;

//Sistemare chi inizia problema con partita salvata
//executeTurn() viene chiamato ogni volta che si ricomincia la partita con save and load
//
public class ConnectFour {
	
	public static void main(String[] args) 
	{
			//Board gb = new Board();
			//Scanner in = new Scanner(System.in);
			//System.out.print(" Please enter your name: ");
			//String name1 = in.next();
			//Color color1 = new Color(255, 0, 0);
			//Token token1 = new Token(color1);
			//Player p1 = new Player(name1, token1);
			//
			//System.out.print(" Please enter your name: ");
			//String name2 = in.next();
			//Color color2 = new Color(0, 255, 0);
			//Token token2 = new Token(color2);
			//Player p2 = new Player(name2, token2);
			//
			//Match match = new Match(gb, p1, p2);
			//ConnectFour cf = new ConnectFour();
			//cf.play(match);
			ConnectFour cf = new ConnectFour();
			try {
				cf.restartMatch("Partita1");
			} catch (FullColumnException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	
	public void setupNewMatch(Match match) {
		
	}
	
	public void restartMatch(String matchName) throws FullColumnException {
		LoadMatch loadPreviousMatch = new LoadMatch(matchName);
		try {
			Match startedMatch = loadPreviousMatch.loadSavedMatch();

			play(startedMatch);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void play(Match match) {
		try {
			match.executeTurn();
		} catch (FullColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		match.getBoard().printBoard();
	
		if (match.getBoard().isDraw())    // Se è finita con un pareggio
		{
		    	System.out.print(" The match ended in a draw.");
		}
		else if (match.getBoard().isWin())    // Se è finita con una vincita
	    {
	    	System.out.print(" The match ended with a win.");
	    }	
	}
	
	//public Color setupColor() {}
}
