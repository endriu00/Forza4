package saveAndLoad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gameComponents.*;

/**
 * Class for saving a match
 */
public class SaveMatch {

	/**
	 * Variable savedMatch is a File variable. 
	 * This is the file in which the program will store the match stats,
	 * like the gameboard overview, the players, players' token color, the turn...
	 * More on this will be explained in each relative method
	 */
	private File savedMatch;
	
	/**
	 * Simple constructor for the class.
	 * It takes as input a string, matchName, that is the name the player will give to the match,
	 * and the match itself.
	 * Internally, the constructor will gather every necessary characteristic of the match
	 * (that will be used when the user would like to restart the match from where it has interrupted it)
	 * and it will create the file whose name derives from the string inserted by the user in a .txt file. 
	 * This file is stored in the res directory coming along with the repo and it is totally platform independent.
	 * 
	 * The constructor simply uses specific and different single-responsibility methods for writing what it needs to
	 * load the match in a second moment.
	 * 
	 * @param matchName is the string representing the name of the match, given by the user
	 * @param match is the playing match
	 */
	public SaveMatch(String matchName, Match match) {
		savedMatch = new File("res" + File.separator + matchName + ".txt");	
		savePlayer(match.getFirstPlayer(), savedMatch, true, false);
		savePlayer(match.getSecondPlayer(), savedMatch, false, true);
		saveTurn(match, savedMatch, true);
		saveBoard(match.getBoard(), savedMatch, true);
	}
	
	/**
	 * Private method for storing player name and player's token color in the file savingFile
	 * In order to make it user readable, in the file is written a short description of the
	 * next data, like "First player: ", and then the data themselves.
	 * 
	 * Note that the color is divided into its components to make it easier to read and to
	 * let its reconstruction in the loading time correct.
	 * 
	 * Note also that the player could be the first player in the match or it could be the second.
	 * In order to recognize them, it has been introduced the isFirstPlayer boolean variable.
	 * If it is true, the player given when invoking the method is the first player, while 
	 * it is the second otherwise.
	 * 
	 * @param player is the player of the match
	 * @param savingFile is the file where data will be stored
	 * @param isFirstPlayer indicates if the player inserted when calling the method is the first or not
	 * @param toBeAppended is a boolean variable used when initializing the FileWriter object.
	 * 		  If set to 'true', the object will append what it is writing to previous data in the file
	 * 		  If set to 'false', the object will write new data starting from the first line of the file, 
	 * 		  possibly overwriting what it was written before.
	 */
	private static void savePlayer(Player player, File savingFile, boolean isFirstPlayer, boolean toBeAppended) {
		try (FileWriter write = new FileWriter(savingFile, toBeAppended)){
			if(isFirstPlayer) {
				write.write("First player: " + player.getName() + ", " + "token color: " 
							+ player.getToken().getTokenColor().getRed()
							+ " " +
							+ player.getToken().getTokenColor().getGreen()
							+ " " +
							+ player.getToken().getTokenColor().getBlue()
							+ "\n");

			}
			else {
				write.write("Second player: " + player.getName() + ", " + "token color: " 
							+ player.getToken().getTokenColor().getRed()
							+ " " +
							+ player.getToken().getTokenColor().getGreen()
							+ " " +
							+ player.getToken().getTokenColor().getBlue()
							+ "\n");

			}
		} catch (IOException e) {
			
		}
	}
	
	
	/**
	 * Private method for storing the gameBoard situation in the file savingFile.
	 * Before inserting anything about the gameBoard state, it is provided a short
	 * information about what follows next in the file (as it is, "Board: ").
	 * 
	 * The idea here is to save token positions in a sequential way, starting from low positions 
	 * to high positions (thinking at the gameBoard in a visual fashion, not in a numerical one).
	 * If the tokens are saved like this, when loading the match and, so, when reinserting the tokens
	 * in the board, it is not necessary to provide another insertToken method, because each
	 * token can be inserted from low levels to high levels.
	 * If we save from high levels to low (say, if we save row 0, col 0 first, row 1 col 0 next, 
	 * so the most high and most left position first and the below one,
	 * in the loading process we hit first the least inserted token, because we cannot insert first 
	 * a token in the highest position and second a token below. So we should provide a new insert method 
	 * that accounts this, but it is easier to save the token this way).
	 * 
	 * To help human readability, in the file it will be written where the token is, 
	 * in the format row, column (*), and the token color, in the common format rgb.
	 * 
	 * @param gameBoard is the gameBoard of the match
	 * @param savingFile is the file where the data will be stored
	 * @param toBeAppended is a boolean variable used when initializing the FileWriter object.
	 * 		  If set to 'true', the object will append what it is writing to previous data in the file
	 * 		  If set to 'false', the object will write new data starting from the first line of the file, 
	 * 		  possibly overwriting what it was written before.
	 */
	private static void saveBoard(Board gameBoard, File savingFile, boolean toBeAppended) {
		try(FileWriter write = new FileWriter(savingFile, toBeAppended)){
			write.write("Board: \n");
			for(int i = gameBoard.getNumberOfRows() - 1; i >= 0; i--) {
				for(int j = 0; j < gameBoard.getNumberOfColumns(); j++) {
					if(gameBoard.getTokenInPosition(i, j) == null) continue;
					write.write("Row: " + String.valueOf(i) + " Col: " + String.valueOf(j) + " " 
								+ gameBoard.getTokenInPosition(i, j).getTokenColor().getRed() 
								+ " " +
								+ gameBoard.getTokenInPosition(i, j).getTokenColor().getGreen() 
								+ " " +
								+ gameBoard.getTokenInPosition(i, j).getTokenColor().getBlue() 
								+ "\n");
				}
			}
			
		}
		catch(IOException e) {
			
		}
	}
	
	/**
	 * Private method for storing the turn variable.
	 * When saving, a player has already started his turn, so the number of turns passed from the beginning
	 * will differ by +1 from the token inserted.
	 * In other words, a player can save before inserting a token, because his turn will automatically finish
	 * when he will have inserted it.
	 * 
	 * @param match is the match being played
	 * @param savingFile is the file storing the infos
	 * @param toBeAppended is a boolean variable used when initializing the FileWriter object.
	 * 		  If set to 'true', the object will append what it is writing to previous data in the file
	 * 		  If set to 'false', the object will write new data starting from the first line of the file, 
	 * 		  possibly overwriting what it was written before.
	 */
	private static void saveTurn(Match match, File savingFile, boolean toBeAppended) {
		try(FileWriter write = new FileWriter(savingFile, toBeAppended)){
			write.write("Turn: " + match.getTurn() + " \n");
			
		}
		catch(IOException e) {
			
		}
	}
	
	
	
	
	
}
