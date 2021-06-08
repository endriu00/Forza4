package testing;

import utilities.IntegrityMatrix;

public class IntegrityMatrixCheck {

	public static void main(String... strings) {
		
		int rows = 6;
		int columns = 7;
		IntegrityMatrix matrix = new IntegrityMatrix(rows, columns);
		
		/**
		 * Not needing a method for inserting an element in the matrix that could be accessible by the external for security reasons,
		 * for testing purpose it will be used the method checkIfAlreadyInserted(int row, int col) in order to insert an element in the matrix.
		 * This might seem cumbersome in some way, because this method should do something completely different and should be tested first, but
		 * modify the matrix externally is not a desired property for this kind of data structure.
		 * 
		 * Consider these calls as a correct placement of the element in a valid position.
		 * 
		 * 
		 * Just for testing purpose!!
		 */
		matrix.checkIfAlreadyInserted(0, 0);
		matrix.checkIfAlreadyInserted(0, 1);
		matrix.checkIfAlreadyInserted(0, 2);
		matrix.checkIfAlreadyInserted(1, 2);
		matrix.checkIfAlreadyInserted(2, 2);
		matrix.checkIfAlreadyInserted(0, 4);
		matrix.checkIfAlreadyInserted(0, 5);
		matrix.checkIfAlreadyInserted(1, 5);
		matrix.checkIfAlreadyInserted(2, 5);
		
		
		
	}
	
	public static void testCheckIfAlreadyInserted(IntegrityMatrix im) {
		
	}
}
