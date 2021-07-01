package customException;

/**
 * Exception class.
 * ColumnOutOfRange class is a custom Exception class that is thrown whenever a token is being inserted in a column that is not in the range of the columns of the domain problem.
 *
 * @author andre
 *
 */
public class ColumnOutOfRange extends Throwable {

	public ColumnOutOfRange() {
		System.out.println("Column number not allowed!");
	}
	
}
