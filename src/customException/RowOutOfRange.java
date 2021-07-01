package customException;

/**
 * Exception class.
 * RowOutOfRange class is a custom Exception class that is thrown whenever a token is being inserted in a row that is not in the range of the columns of the domain problem.
 * @author andre
 *
 */
public class RowOutOfRange extends Throwable {
	
	public RowOutOfRange() {
		System.out.println("Row number not allowed, because out of range!");
	}
}
