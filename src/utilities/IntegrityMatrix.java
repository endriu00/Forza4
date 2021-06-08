package utilities;

import customException.ColumnOutOfRange;
import customException.RowOutOfRange;

/**
 * class for checking the integrity of the Board contained in the saved file.
 */
public class IntegrityMatrix {

	/**
	 * checkMatrix is private so that it cannot be accessed directly from other classes than this.
	 * Basically, checkMatrix is a matrix representing the Board (it has the same number of rows and columns), 
	 * with the difference that each position stores either 0 or 1:
	 *  - 0 if the representing Board has no Token in the position where the 0 is stored
	 *  - 1 if the representing Board has a Token in that position
	 * The checkMatrix is particular useful against file changed by human hands attempting to mess up
	 * the file of the saved match, trying to change the normal behavior of the token insertion in the Board.
	 * Further details about the kind of prevented threats are specified in the method checkIntegrity,
	 * where it is effectively checked whether the integrity of the Board read from file is checked.
	 */
	private int[][] checkMatrix;
	
	/**
	 * Basic constructor. 
	 * @param rows is the number of rows of the matrix
	 * @param cols is the number of columns of the matrix
	 */
	public IntegrityMatrix(int rows, int cols) {
		checkMatrix = new int[rows][cols];
		this.populateMatrixWith0s();
	}
	
	/**
	 * Method for initializing checkMatrix with zeros.
	 * In other words, we are saying that there is initially no object inside the matrix (so as in the Board the matrix is representing)
	 */
	private void populateMatrixWith0s() {
		for(int i = 0; i < checkMatrix.length; i++) {
			for(int j = 0; j < checkMatrix[0].length; j++) {
				checkMatrix[i][j] = 0;
			}
		}
	}
	
	/**
	 * Check if someone tampered the file, so if someone modified any number of rows creating an Illegal State. Does not check if someone created a Legal State for the match. 
	 * For Illegal State we define a state in which there is a violation in the inserting of a token, so a token that seems "flying", with no token below it, 
	 * or a token placed over an other token. 
	 * Sadly this does not check for Legal State, where for Legal State it is meant a state in which a match is different from the original, 
	 * but does not violate Illegal State rules.
	 * @param i is the integer representing the row of the Board it is necessary to check the integrity of
	 * @param j is the integer representing the column of the Board it is necessary to check the integrity of
	 * @return true if checkIfAlreadyInserted returns false and checkIfProperCreatedColumn returns true,
	 * 		   false in each other case.
	 */
	public boolean checkIntegrity(int i, int j) {
		if(!checkIfAlreadyInserted(i, j) && checkIfProperCreatedColumn(i, j)) return true;
		else return false;
	}
	
	//If the column has a missing piece, this method will find it out.
			/**
			 * Eg:   _ _ _ _ _ _ _ 
			 * 		|	   -	  |
			 *  
			 * 		|	   -	  |
			 * 
			 * 		|	   X	  |
			 * 
			 * 		|	   -	  |
			 * 
			 * 		|	   -	  |
			 * 
			 * 		|	   -      |
			 * 
			 * 		|_ _ _ _ _ _ _|
			 * 
			 * where: 
			 * . x stands for the missing element for a well formed column.
			 * . - stands for an element in the column.   
			 */
	/**
	 * This method stands against file in which certain elements of the same column but different row are deleted, or modified, by a user.
	 * In particular, if one of the element representing a column in the game is missing, this method will return false, letting checkIntegrity method be false.
	 * In order to achieve this task, given the row the element is trying to be inserted in, the method checks if the below rows (rows greater than the given row till the end of the checkMatrix)
	 * are set to 0, so if there is a NO element in the rows below.
	 * 
	 * Checks also if the row and the column passed in input are within the bound of the matrix.
	 * 
	 * Note that if the first element of the column is missing, this method won't notice it, but this case is handled in the LoadMatch class because it is outside of this class concern.
	 * @param row is the row of the element to be checked.
	 * @param col is the column of the element to be checked.
	 * @return true if the column is a proper column, so if there is no missing piece in the column (so no floating token in the Board)
	 * 		   false if the column is wrong, so if there is not an element between other two elements in a vertical assessment. 
	 */
	public boolean checkIfProperCreatedColumn(int row, int col) {
		
		//Handling out of bounds row and column numbers.
		try {
			if(col < 0 || col > 6) throw new ColumnOutOfRange();
		}
		catch(ColumnOutOfRange e) {
			return false;
		}
		
		try {
			if(row < 0 || row > 5) throw new RowOutOfRange();
		}
		catch(RowOutOfRange e) {
			return false;
		}
		
		while(row < checkMatrix.length) {
			if(checkMatrix[row][col] == 0) {
				return false;
			}
			row++;
		}
		return true;
	}
	
	/**
	 * This method checks whether the file has a duplicate inside of the token positions part, so if a token position is registered more than once.
	 * Simply, it verifies if the element we are trying to insert in the corresponding Board already exists 
	 * (if exists in this checkMatrix, given the mapping between the Board and the checkMatrix).
	 * If it exists, there is a problem, so it has to respond true (it is already inserted!), changing return answer of checkIntegrity method to false, otherwise it is a new element,
	 * so it in inserted in the checkMatrix and passes this control, returning false.
	 * 
	 * Checks also if the row and the column passed in input are within the bound of the matrix.
	 * 
	 * @param row is the row of the element to be checked.
	 * @param col is the column of the element to be checked.
	 * @return true if the element is already in the checkMatrix, 
	 * 		   false if it is a new element.
	 */
	public boolean checkIfAlreadyInserted(int row, int col) {
		
		//Handling out of bounds row and column numbers.
		try {
			if(col < 0 || col > 6) throw new ColumnOutOfRange();
		}
		catch(ColumnOutOfRange e) {
			return true;
		}
		
		try {
			if(row < 0 || row > 5) throw new RowOutOfRange();
		}
		catch(RowOutOfRange e) {
			return true;
		}
		
		//If the value of the cell was zero, there was no element stored, otherwise there was the element
		if(this.checkMatrix[row][col] == 0) {
			this.checkMatrix[row][col] = 1;
			return false;
		}
		
		return true;
	}
	
	/**
	 * Utility getter method for getting the element in i, j position
	 * @param i indicates the row of the matrix
	 * @param j indicates the column of the matrix
	 * @return the element in position i, j
	 */
	public int getElemRow(int i, int j) {
		return checkMatrix[i][j];
	}
}
