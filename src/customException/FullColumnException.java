package customException;

/**
 * FullColumnException class is a class for creating a customException.
 * The structure is simple:
 * - the class extends Throwable in order to be considered an exception.
 * - when creating an object of this type, it initializes the variable errorString to 
 * 	 the correct informative error string.
 * - it has a method for getting this error string.
 * 
 * @author andre
 *
 */
public class FullColumnException extends Throwable{
	
	private String errorString;

	public FullColumnException() {
		errorString = "The column is full! Please try inserting the token in another allowed, not-full column!";
	}
	
	public String getError() {
		return errorString;
	}
}
