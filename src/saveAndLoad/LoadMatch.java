package saveAndLoad;

import gameComponents.*;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import customException.CorruptedFileException;
import customException.FullColumnException;
import gameComponents.Match;
import gameComponents.Player;
import utilities.IntegrityMatrix;

/**
 * Class LoadMatch is the class supposed to give methods for loading a match from a file.
 * Obviously, this file must be in a specific pattern (that is, the pattern in which it is saved, in the SaveMatch class).
 * If the file is provided in a different way, LoadMatch will raise an exception.
 * In order to adhere to the Observer design pattern, this class must implement LoadInterface interface.
 * 
 * @author andre
 *
 */
public class LoadMatch implements LoadInterface {	
		
	/**
	 * This method takes as input a string, that is the file name, so that an object of type Scanner can access (and read from) it.
	 * In order to separate methods responsibilities, it has been created a method for determining each component of the match constructor, 
	 * so we have loadPlayer to load a type Player object, loadTurn to retrieve the turn of the saved match and so on.
	 * 
	 * If the method isTampered returns true (signifying that the file the scanner is reading is corrupted or not properly modified),
	 * throws a new CorruptedFileException to be handled in a calling class.
	 * 
	 * @param fileName is the name of the file from which load the match.
	 * @throws FileNotFoundException when file trying to read is not found
	 * @throws CorruptedFileException when file trying to read is corrupted
	 * @return an object of type Match, the match that is correctly rebuilt
	 */
	public Match loadMatch(String fileName) throws FileNotFoundException, CorruptedFileException {

		Match loadedMatch = null;
		File loadingFile = new File("savedGames" + File.separator + fileName);
		Scanner fileScanner = new Scanner(loadingFile);
		Player firstPlayer = loadPlayer(loadingFile, fileScanner, true);
		Player secondPlayer = loadPlayer(loadingFile, fileScanner, false); 
		int turn = loadTurn(loadingFile, fileScanner);
		Board gameBoard = loadBoard(loadingFile, fileScanner, firstPlayer, secondPlayer);

		Scanner tokenCounterScanner = new Scanner(loadingFile);
		int numberOfTokensInBoard = countTokens(loadingFile, tokenCounterScanner);
		if(!isTampered(turn, numberOfTokensInBoard)){
			//Store new crafted match in loadedMatch variable
			loadedMatch = new Match(gameBoard, firstPlayer, secondPlayer);
			//Finally, changes the turn (in the constructor it is always initialized to 1) to the correct turn
			loadedMatch.setTurn(turn);
			return loadedMatch;
		}
		else throw new CorruptedFileException();
	}
	
	/**
	 * Method used to load the player from the given file, fileName.
	 * It uses a Scanner object, fileScanner, to scan the file and to find the statement
	 * "First player: " if the boolean parameter isFirstPlayer is set to true, 
	 * or the statement "Second player: " in the case isFirstPlayer is set to false.
	 * 
	 * It is assumed the file is in the format used to create it in the SaveMatch class, 
	 * so the player part is in the form 
	 * 
	 * "First/Second player: playerName, token color: colorRedComponent colorGreenComponent colorBlueComponent"
	 * 
	 * At the end, it is created the player object of type Player, that has the name read from file and the 
	 * token color constructed from the color components read in the file too.
	 * 
	 * @param fileName is the file.
	 * @param fileScanner is the scanner that reads the file.
	 * @param isFirstPlayer indicates if the player reading is the first or not.
	 * @return
	 */
	private static Player loadPlayer(File fileName, Scanner fileScanner, boolean isFirstPlayer) {
		Player player;
		Color tokenColor;
		Token token;
		
		//If the player is the first player
		if(isFirstPlayer) {
			fileScanner.findInLine("First player: ");				//Searches for the statement "First player"
			fileScanner.useDelimiter(",");							//Uses delimiter to skip the comma and leave it out of the firstPlayerName
			String firstPlayerName = fileScanner.next(); 			//Takes the name
			fileScanner.reset();									//Resets Scanner initial state
			fileScanner.findInLine("token color: ");				//Searches for the statement "token color: "
			int redComp = fileScanner.nextInt();					//Takes red component 
			int greenComp = fileScanner.nextInt();					//Takes green component 
			int blueComp = fileScanner.nextInt();					//Takes blue component 
			tokenColor = new Color(redComp, greenComp, blueComp);	//Forms the color
			token = new Token(tokenColor);							//Forms the token
			player = new Player(firstPlayerName, token);			//Forms the player
			fileScanner.nextLine();									//Advances past the next line
		}
		//If the player is the second player
		else {
			fileScanner.findInLine("Second player: ");				//Searches for the statement "Second player"                               
			fileScanner.useDelimiter(",");                          //Uses delimiter to skip the comma and leave it out of the secondPlayerName
			String secondPlayerName = fileScanner.next();           //Takes the name                                                          
			fileScanner.reset();                                    //Resets Scanner initial state                                            
			fileScanner.findInLine("token color: ");                //Searches for the statement "token color: "                              
			int redComp = fileScanner.nextInt();                    //Takes red component                                                     
			int greenComp = fileScanner.nextInt();                  //Takes green component                                                   
			int blueComp = fileScanner.nextInt();                   //Takes blue component                                                                                                                                                          
			tokenColor = new Color(redComp, greenComp, blueComp);   //Forms the color                                                         
			token = new Token(tokenColor);                          //Forms the token                                                         
			player = new Player(secondPlayerName, token);           //Forms the player                                                        
			fileScanner.nextLine();									//Advances past the next line

		}
		return player;
	}
	
	/**
	 * Method used to load the turn from the given file, fileName.
	 * It uses a Scanner object, fileScanner, to scan the file. 
	 * It is assumed the file is in the format used to create it in the SaveMatch class, 
	 * so the turn part is in the form:
	 * 
	 * "Turn: X"
	 * 
	 * where X is a number
	 * 
	 * At the end, it is returned the turn.
	 * @param fileName is the file.
	 * @param fileScanner is the scanner that reads the file.
	 * @return the int turn.
	 */
	private static int loadTurn(File fileName, Scanner fileScanner) {
		fileScanner.findInLine("Turn: ");							//Searches for the statement "Turn: "
		int turn = fileScanner.nextInt();							//Takes the turn
		fileScanner.nextLine();										//Advances past the next line
		return turn;
	}
	
	/**
	 * Method for counting the number of token listed in the file.
	 * 
	 * @param fileName is the file containing the saved match
	 * @param fileScanner is the Scanner object.
	 * @return the number of token counted.
	 */
	private static int countTokens(File fileName, Scanner fileScanner) {
		int tokens = 0;
		//First phase: find Board section of the file 
		fileScanner.nextLine();
		fileScanner.nextLine();
		fileScanner.nextLine();
		fileScanner.nextLine();		

		//Count how many tokens there are in the board
		while(fileScanner.hasNextLine()) {
			fileScanner.nextLine();
			tokens++;
		}
		return tokens;
	}
	
	/**
	 * Method used to load the board from the given file, fileName.
	 * It uses a Scanner object, fileScanner, to scan the file. 
	 * It is assumed the file is in the format used to create it in the SaveMatch class, 
	 * so the board part is in the form:
	 * 
	 * "Board: 
	 * Row: x Col: y tokenColorRedComp tokenColorGreenComp tokenColorBlueComp 
	 * . . . "
	 * 
	 * While there is another line in the file, it finds each value of the token color and inserts it into the Board, 
	 * but only if it satisfy the wise checkIntegrity method.
	 * 
	 * At the end, it is returned the gameBoard.
	 * 
	 * @param fileName is the file.
	 * @param fileScanner is the scanner that reads the file.
	 * @return the Board gameBoard
	 * @throws FullColumnException 
	 * @throws CorruptedFileException 
	 */
	private static Board loadBoard(File fileName, Scanner fileScanner, Player firstPlayer, Player secondPlayer) throws CorruptedFileException {	
		
		Board gameBoard = new Board();
		//Initialize the checkMatrix to the number of rows and columns of the board, so that it is like a mirror to the board
		IntegrityMatrix checkMatrix = new IntegrityMatrix(Board.getNumberOfRows(), Board.getNumberOfColumns());
		
		//First phase for identifying the statement "Board" and going forward
		fileScanner.next();
		fileScanner.nextLine();
		
		//Local variables 
		int currentRow;
		int currentColumn;
		int redComp = 0;
		int greenComp = 0;
		int blueComp = 0;
		Token firstToken = firstPlayer.getToken();
		Token secondToken = secondPlayer.getToken();
		
		//Reads where are the tokens in the gameBoard from the provided file, fileName 
		while(fileScanner.hasNextLine()) {																								
			fileScanner.findInLine("Row: ");        //Searches for the statement "Row"                                
			currentRow = fileScanner.nextInt();     //Takes the row number
	                                                                                                                                                       
			fileScanner.findInLine("Col: ");                                                                                                                                                                                           
			currentColumn = fileScanner.nextInt();  //Takes the column number                         
			
			//Check the integrity of the position.      
			//checkMatrix is the IntegrityMatrix object used to verify that the file leads to a Legal State for the Board,
			//so if the Board recreated is properly formed, without errors like floating tokens or tokens above tokens.
			//For further details, consult the IntegrityMatrix class.
			if(checkMatrix.checkIntegrity(currentRow, currentColumn, Board.getNumberOfRows()-1, Board.getNumberOfColumns()-1)) {																   
				//Token creation
				redComp = fileScanner.nextInt();
				greenComp = fileScanner.nextInt();
				blueComp = fileScanner.nextInt();
				//If token belongs to firstPlayer or to secondPlayer. Inserting a new object of type token would lead to insert different objects at all, with different references
				//This would lead to n different tokens in the board, so logic would be completely gone.
				if(redComp == firstToken.getTokenColor().getRed() && greenComp == firstToken.getTokenColor().getGreen() && blueComp == firstToken.getTokenColor().getBlue()) {
					//Insert the token in the gameBoard
					try {
						gameBoard.insert(firstToken, currentColumn);
					} catch (FullColumnException e) {
						throw new CorruptedFileException();
					}
				}
				else if(redComp == secondToken.getTokenColor().getRed() && greenComp == secondToken.getTokenColor().getGreen() && blueComp == secondToken.getTokenColor().getBlue()) {	
					//Insert the token in the gameBoard
					try {
						gameBoard.insert(secondToken, currentColumn);
					} catch (FullColumnException e) {
						throw new CorruptedFileException();
					}
				}	
			}
			else {
				//Integrity matrix says it is not safe to open the file because the board was wrong. 
				throw new CorruptedFileException();
			}
			//Advances past the line
			fileScanner.nextLine();	
		}
		return gameBoard;		
	}
	
	/**
	 * The purpose of this method is to check if the file trying to read is tampered, that happens in a variety of cases, but this checks
	 * that the bound numberOfTurns = numberOfTokenInserted + 1 (+1 because the first turn is the turn 1
	 * and when a player save the match, he can save it only at the beginning of his turn, so it must be that the number of turns passed
	 * are greater than the number of token inserted in the Board + 1).
	 * This check is not included in the IntegrityMatrix class because it is linked to the number of turns, information written in the saved file,
	 * so it is strictly a concern of this class.	 
	 * @param turn is the turn value
	 * @param numberOfTokens is the number of tokens in the board
	 * @return true if the file was tampered, so if someone inserted one or more token without hand crafting a correct value for turn, false otherwise.
	 */
	private static boolean isTampered(int turn, int numberOfTokens) {
		if(turn != numberOfTokens + 1) {
			return true;
		}
		return false;
	}	
}
