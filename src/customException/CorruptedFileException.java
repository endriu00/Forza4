package customException;

/**
 * CorruptedFileException class is a class for creating a customException.
 * The structure is simple:
 * - the class extends Throwable in order to be considered an exception.
 * - when creating an object of this type, it initializes the variable errorString to 
 * 	 the correct informative error string.
 * - it has a method for getting this error string.
 * 
 * @author andre
 *
 */
public class CorruptedFileException extends Throwable {

	private String errorString;
	
	public CorruptedFileException() {
		errorString = "File trying to read is corrupted.";
	}
	
	public String getError() {
		return errorString;
	}
}
