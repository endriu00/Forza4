package customException;

/**
 * SavingFileException class is a class for creating a customException.
 * The structure is simple:
 * - the class extends Throwable in order to be considered an exception.
 * - when creating an object of this type, it initializes the variable errorString to 
 * 	 the correct informative error string.
 * - it has a method for getting this error string.
 * 
 * @author andre
 *
 */
public class SavingFileException extends Throwable {
	
	private String error;
	
	public SavingFileException() {
		error = "It was not possible to save this match!";
	}
	
	public String getError() {
		return error;
	}

}
