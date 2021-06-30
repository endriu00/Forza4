package testing;

import customException.FullColumnException;

import java.awt.Color;
import gameComponents.Board;
import gameComponents.Token;

/**
 * Class for testing methods and behavior of the class Board, as this class name suggests.
 * This is fundamentally a unit testing.
 * In the class, it has been written, for each test, the purpose of the test, so a short explanation, 
 * an expected result, and the effective result of the test. Easy to understand, if the expected result
 * and the result itself are the same, the test has been successful.
 * In order to view these tests, you are provided a main method that will show you the testing results 
 * in the standard output view. To not harden the view, it has been provided a catchy view.
 * 
 * Talking about the structure of the class, each test has got its static method.
 * Each method is then invoked in the main.
 * 
 * @author andre
 */
public class BoardTester {
	
	public static void main(String...strings) throws FullColumnException {
		
		Board gameBoard = new Board();
		Token testingToken = new Token(new Color(0, 0, 0));
		Token otherTestingToken = new Token(new Color(255, 255, 255));
		
		System.out.println("Test for a column number out of bound. "
				+ "This means a test for a column that has a number greater than the number of columns "
				+ "in the board.");
		System.out.println("Expected: Column number not allowed!");
		System.out.println("Testing...");
		testInsertOutOfColumnBound(gameBoard, testingToken);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		gameBoard = new Board();
		System.out.println("Test for an insert in a full column.");
		System.out.println("Expected: The column is full! Please try inserting the token in another allowed, not-full column!");
		System.out.println("Testing...");
		testFullColumn(gameBoard);		
		System.out.println("Test finished.\n");

		System.out.println("---------------------------------------------------------------------\n");

		gameBoard = new Board();
		System.out.println("Test for verifying the board can be full. In this case, it does not matter that every token is the same.\n"
				+ "Another test has been made for verifying one can win with the correct rules!");
		System.out.println("isBoardFull method output must be true.\n"
				+ "Expected: true.");
		System.out.println("Testing...");
		testIsBoardFull(gameBoard, testingToken);
		System.out.println("Test finished.\n");

		System.out.println("---------------------------------------------------------------------\n");
		
		gameBoard = new Board();
		System.out.println("Test for verifying there can be a diagonal win, ie a win with the same tokens placed in a diagonal pattern,\n"
				+ "with the diagonal starting at the bottom and ending up upgoing to the right.");
		System.out.println("isBottomRightDiagonalWin must be true.\n"
				+ "Expected: true.");
		System.out.println("Testing...");
		testIsBottomRightDiagonalWin(gameBoard, testingToken, otherTestingToken);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		gameBoard = new Board();
		System.out.println("Test for verifying there can be a diagonal win, ie a win with the same tokens placed in a diagonal pattern,\n"
				+ "with the diagonal starting at the bottom and ending up upgoing to the left.");
		System.out.println("isBottomLeftDiagonalWin must be true.\n"
				+ "Expected: true.");
		System.out.println("Testing...");
		testIsBottomLeftDiagonalWin(gameBoard, testingToken, otherTestingToken);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		gameBoard = new Board();
		System.out.println("Test for verifying there can be a vertical win, ie a win with the same tokens placed in a vertical pattern");
		System.out.println("isVerticalWin must be true.\n"
				+ "Expected: true.");
		System.out.println("Testing...");
		testIsVerticalWin(gameBoard, testingToken);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		gameBoard = new Board();
		System.out.println("Test for verifying there can be a horizontal win, ie a win with the same tokens placed in a horizontal pattern");
		System.out.println("isHorizontalWin must be true.\n"
				+ "Expected: true.");
		System.out.println("Testing...");
		testIsHorizontalWin(gameBoard, testingToken);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		gameBoard = new Board();
		System.out.println("Test for verifying there can be a draw, ie a situation in which no player wins, so the board is full but no onw wins.");
		System.out.println("isDraw must be true.\n"
				+ "Expected: true.");
		System.out.println("Testing...");
		testIsDraw(gameBoard, testingToken, otherTestingToken);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		gameBoard = new Board();
		System.out.println("Test for getting the token in position i, j, so testing getTokenInPosition(int i, int j) method.");
		System.out.println("This method returns a token (read the address of a token).\n"
				+ "Expected: gameComponents.Token@someNumbers.");
		System.out.println("Testing...");
		testGetTokenInPosition(gameBoard, testingToken);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for getting the number of rows of the board.\n"
				+ "Note that it is a static, final value because of its definition in the domain of the project.");
		System.out.println("It is expected an int representing the number of rows.\n"
				+ "Expected: 6");
		System.out.println("Testing...");
		testGetRows();
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");


		System.out.println("Test for getting the number of columns of the board.\n"
				+ "Note that it is a static, final value because of its definition in the domain of the project.");
		System.out.println("It is expected an int representing the number of columns.\n"
				+ "Expected: 7");
		System.out.println("Testing...");
		testGetColumns();
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
	}
		
	public static void testInsertOutOfColumnBound(Board board, Token token) {
		int notAColumnInBound = 7;
		
		//Try catch outside the purpose of this method. Just to ensure it has no error at compile time.
		try {
			board.insert(token, notAColumnInBound);
		}
		catch(FullColumnException e) {
		}
		
	}
	
	public static void testFullColumn(Board board) {
		Token token = new Token(new Color(0, 0, 0));
		
		int column = 2;
		for(int i = 0; i < 7; i++) {
			try {
				board.insert(token, column);
			} catch (FullColumnException e) {
				System.out.println(e.getError());
			}
			board.printBoard();

		}
	}
	
	public static void testIsBoardFull(Board board, Token token1) {
		
		//Try catch outside the purpose of this method. Just to ensure it has no error at compile time.
		try {
			for(int i = board.getNumberOfRows() - 1; i >= 0; i--) {
				for(int j = 0; j < board.getNumberOfColumns(); j++) {
					board.insert(token1, j);
				}
			}
			board.printBoard();
			System.out.println("Actually is: " + board.isBoardFull());
			
		}
		catch(FullColumnException e){
			
		}
	}
	
	public static void testIsDraw(Board board, Token token1, Token token2) {
		
		//Try catch outside the purpose of this method. Just to ensure it has no error at compile time.
		try {
			
			//first row -- from bottom to top
			board.insert(token1, 0);
			board.insert(token2, 1);
			board.insert(token1, 2);
			board.insert(token2, 3);
			board.insert(token1, 4);
			board.insert(token2, 5);
			board.insert(token1, 6);
			
			//second row
			board.insert(token2, 0);
			board.insert(token1, 1);
			board.insert(token2, 2);
			board.insert(token1, 3);
			board.insert(token2, 4);
			board.insert(token1, 5);
			board.insert(token2, 6);
			
			//third row
			board.insert(token1, 0);
			board.insert(token2, 1);
			board.insert(token1, 2);
			board.insert(token2, 3);
			board.insert(token1, 4);
			board.insert(token2, 5);
			board.insert(token1, 6);
			
			//fourth row
			board.insert(token2, 1);
			board.insert(token1, 0);
			board.insert(token2, 3);
			board.insert(token1, 2);
			board.insert(token2, 5);
			board.insert(token1, 4);
			board.insert(token1, 6);
			
			//fifth row
			board.insert(token1, 1);
			board.insert(token2, 0);
			board.insert(token1, 3);
			board.insert(token2, 2);
			board.insert(token1, 5);
			board.insert(token2, 4);
			board.insert(token2, 6);
			
			//sixth row
			board.insert(token2, 0);
			board.insert(token1, 1);
			board.insert(token2, 2);
			board.insert(token1, 3);
			board.insert(token2, 4);
			board.insert(token1, 5);
			board.insert(token2, 6);
			
			board.printBoard();
			
			System.out.println("Actually is: " + board.isDraw());

		}
		catch(FullColumnException e) {
			
		}					
	}
	
	public static void testIsHorizontalWin(Board board, Token token1) {
		//Try catch outside the purpose of this method. Just to ensure it has no error at compile time.
		try {
			board.insert(token1, 0);
			board.insert(token1, 1);
			board.insert(token1, 2);
			board.insert(token1, 3);
			
			board.printBoard();
			
			System.out.println("Actually is: " + board.isHorizontalWin());

		}
		catch(FullColumnException e) {
			
		}			
	}
	
	public static void testIsVerticalWin(Board board, Token token1) {
		//Try catch outside the purpose of this method. Just to ensure it has no error at compile time.
		try {
			board.insert(token1, 0);
			board.insert(token1, 0);
			board.insert(token1, 0);
			board.insert(token1, 0);
			
			board.printBoard();
			
			System.out.println("Actually is: " + board.isVerticalWin());

		}
		catch(FullColumnException e) {
			
		}	
	}
	
	public static void testIsBottomLeftDiagonalWin(Board board, Token token1, Token token2) {
		
		//Try catch outside the purpose of this method. Just to ensure it has no error at compile time.
		try {
			//First row - from bottom to top
			board.insert(token1, 3);
			board.insert(token2, 2);
			board.insert(token1, 1);
			board.insert(token2, 0);
			
			//Second row
			board.insert(token1, 2);
			board.insert(token2, 1);
			board.insert(token1, 0);
			
			//Third row
			board.insert(token2, 0);
			board.insert(token1, 1);
			
			//Fourth row
			board.insert(token2, 1);
			board.insert(token1, 0);
			
			board.printBoard();
			
			System.out.println("Actually is: " + board.isBottomLeftDiagonalWin());

		}
		catch(FullColumnException e) {
			
		}
	}
	
	public static void testIsBottomRightDiagonalWin(Board board, Token token1, Token token2) {
		
		//Try catch outside the purpose of this method. Just to ensure it has no error at compile time.
		try {
			//First row - from bottom to top
			board.insert(token1, 0);
			board.insert(token2, 1);
			board.insert(token1, 2);
			board.insert(token2, 3);
			
			//Second row
			board.insert(token1, 1);
			board.insert(token2, 2);
			board.insert(token1, 3);
			
			//Third row
			board.insert(token2, 3);
			board.insert(token1, 2);
			
			//Fourth row
			board.insert(token2, 2);
			board.insert(token1, 3);
			
			board.printBoard();
			
			System.out.println("Actually is: " + board.isBottomRightDiagonalWin());
			
		}
		catch(FullColumnException e) {
			
		}
	}
	
	public static void testGetTokenInPosition(Board board, Token token1) {
		int posInRow = 5;
		int posInCol = 5;
		try {
			board.insert(token1, posInCol);
		}
		catch(FullColumnException e) {
			
		}
		
		Token token = board.getTokenInPosition(posInRow, posInCol);
		System.out.println("Actually is: " + token1);
		
	}
	
	public static void testGetRows() {
		System.out.println("Actually is: " + Board.getNumberOfRows());
	}
	
	public static void testGetColumns() {
		System.out.println("Actually is: " + Board.getNumberOfColumns());
	}

}
