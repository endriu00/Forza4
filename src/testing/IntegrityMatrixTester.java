package testing;

import utilities.IntegrityMatrix;

/**
 * Testing the class IntegrityMatrix with all of its most important methods.
 * @author andre
 *
 */
public class IntegrityMatrixTester {

	public static void main(String... strings) {
		
		int rows = 6;
		int columns = 7;
		IntegrityMatrix matrix = new IntegrityMatrix(rows, columns);
		
		/**
		 * Not needing a method for inserting an element in the matrix that could be accessible by the external for security reasons,
		 * for testing purpose it will be used the method checkIfAlreadyInserted(int row, int col) in order to insert an element in the matrix.
		 * This might seem cumbersome in some way, because this method should do something completely different and should be tested first, but
		 * modifying the matrix externally is not a desired property for this kind of data structure.
		 * 
		 * Consider these calls as a correct placement of the element in a valid position.
		 * 
		 * 
		 * Just for testing purpose!!
		 */
		matrix.checkIfAlreadyInserted(0, 0, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(0, 1, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(0, 2, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(1, 2, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(2, 2, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(0, 4, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(0, 5, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(1, 5, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(2, 5, rows-1, columns-1);
		
		System.out.println("Test for an element inserted into the matrix in a row out of given bound.\n"
				+ "This means testing the method isRowBoundRespected(...).\nThere will be shown two tests, the first one testing a situation in which\n"
				+ "an element respects the given bound, and then a test for an element not respecting it.\nThe method being tested returns a boolean"
				+ " so true means that, in this case,\nit is true that an element respects the bound, and false means the opposite.\n");
		System.out.println("Expected: true");
		System.out.println("Testing[1/2]...");
		testIsRowBoundRespected(matrix, 0, rows-1);
		System.out.println("\nExpected: false");
		System.out.println("Testing[2/2]...");
		testIsRowBoundRespected(matrix, rows+1, rows-1);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for an element inserted into the matrix in a column out of given bound.\n"
				+ "This means testing the method isColBoundRespected(...).\nThere will be shown two tests, the first one testing a situation in which\n"
				+ "an element respects the given bound, and then a test for an element not respecting it.\nThe method being tested returns a boolean"
				+ " so true means that, in this case,\nit is true that an element respects the bound, and false means the opposite.\n");
		System.out.println("Expected: true");
		System.out.println("Testing[1/2]...");
		testIsColBoundRespected(matrix, 2, columns-1);
		System.out.println("\nExpected: false");
		System.out.println("Testing[2/2]...");
		testIsColBoundRespected(matrix, columns+1, columns-1);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		System.out.println("Test for an element inserted into the matrix in a position already occupied by another element.\n"
				+ "This means testing the method checkIfAlreadyInserted(...).\nThere will be shown two tests, the first one testing a situation in which\n"
				+ "an element was effectively already present, and then a test for an element not present before.\nThe method being tested returns a boolean"
				+ " so true means that, in this case,\nit is true that an element was already inserted, and false means the opposite.\n");
		System.out.println("Expected: true");
		System.out.println("Testing[1/2]...");
		testCheckIfAlreadyInserted(matrix, 0, 0, rows-1, columns-1);
		System.out.println("\nExpected: false");
		System.out.println("Testing[2/2]...");
		testCheckIfAlreadyInserted(matrix, 0, 3, rows-1, columns-1);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");
		
		matrix = new IntegrityMatrix(rows, columns);
		
		//Adding elements beginning from the bottom of the matrix to the top in the columns 5. 
		//It is added every element from row 5 to row 3. Then it is added an element in row 1, skipping row 2.
		//Test will present this situation and decide accordingly.
		matrix.checkIfAlreadyInserted(5, 5, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(4, 5, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(3, 5, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(1, 5, rows-1, columns-1);

		
		System.out.println("Test for an element inserted into the matrix in a position that does not respect a structure of a column,\nso one or more elements are not one above another.\n"
				+ "This means testing the method checkIfProperCreatedColumn(...).\nThere will be shown two tests, the first one testing a situation in which\n"
				+ "a column is properly created, and then a test for a column with a missing piece in it.\nThe method being tested returns a boolean"
				+ " so true means that, in this case,\nit is true that a column is properly created, and false means the opposite.\n"
				+ "It has been added elements beginning from the bottom of the matrix to the top in the columns 5. \n" 
				+ "It is added every element from row 5 to row 3. Then it is added an element in row 1, skipping row 2.\n" 
				+ "Test will present this situation and decide accordingly.\n");
		System.out.println("Expected: true");
		System.out.println("Testing[1/2]...");
		testCheckIfProperCreatedColumn(matrix, 3, 5, rows-1, columns-1);
		System.out.println("\nExpected: false");
		System.out.println("Testing[2/2]...");
		testCheckIfProperCreatedColumn(matrix, 1, 5, rows-1, columns-1);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	
		
		matrix = new IntegrityMatrix(rows, columns);
		matrix.checkIfAlreadyInserted(5, 5, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(4, 5, rows-1, columns-1);
		matrix.checkIfAlreadyInserted(3, 5, rows-1, columns-1);
		
		System.out.println("Test for an element inserted into the matrix in a position that does not violate the integrity of the matrix,\n"
				+ "(the definition of integrity is in the IntegrityMatrix class).\n"
				+ "This means testing the method checkIntegrity(...).\nThere will be shown two tests, the first testing a situation in which\n"
				+ "the matrix is not correct (it has the same problem as above,\nit will be missing a token in a row, the second of the column 5)\n"
				+ "The second test case is done after the inserting of the missing element in the row=2, so it must be the case that, testing the position above\n"
				+ "(position above because checkIntegrity(...) is simply an and of the other two check methods,\n"
				+ "and checkIfAlreadyInserted(...) enters an element in the matrix)"
				+ ", the method returns true\n"
				+ "The method being tested returns a boolean"
				+ " so true means that, in this case,\nit is true that the matrix is integer, and false means the opposite.\n");
		System.out.println("Expected: false");
		System.out.println("Testing[1/2]...");
		testCheckIntegrity(matrix, 3, 5, rows-1, columns-1);
		System.out.println("\nExpected: true");
		System.out.println("Testing[2/2]...");
		matrix.checkIfAlreadyInserted(2,  5,  rows-1,  columns-1);
		testCheckIntegrity(matrix, 1, 5, rows-1, columns-1);
		System.out.println("Test finished.\n");
		
		System.out.println("---------------------------------------------------------------------\n");	

	}
	
	public static void testIsRowBoundRespected(IntegrityMatrix im, int testingRow, int boundRow) {
		boolean answer = im.isRowBoundRespected(testingRow, boundRow);
		System.out.println("Actually is: " + answer);
	}
	
	public static void testIsColBoundRespected(IntegrityMatrix im, int testingCol, int boundCol) {
		boolean answer = im.isColBoundRespected(testingCol, boundCol);
		System.out.println("Actually is: " + answer);
	}	
		
	public static void testCheckIfAlreadyInserted(IntegrityMatrix im, int testingRow, int testingCol, int boundRow, int boundCol) {
		boolean answer = im.checkIfAlreadyInserted(testingRow, testingCol, boundRow, boundCol);
		System.out.println("Actually is: " + answer);
	}
	
	public static void testCheckIfProperCreatedColumn(IntegrityMatrix im, int testingRow, int testingCol, int boundRow, int boundCol) {
		boolean answer = im.checkIfProperCreatedColumn(testingRow, testingCol, boundRow, boundCol);
		System.out.println("Actually is: " + answer);
	}
	
	public static void testCheckIntegrity(IntegrityMatrix im, int testingRow, int testingCol, int boundRow, int boundCol) {
		boolean answer = im.checkIntegrity(testingRow, testingCol, boundRow, boundCol);
		System.out.println("Actually is: " + answer);
	}
}
