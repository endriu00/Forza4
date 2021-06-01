package customException;

public class ColumnOutOfRange extends Throwable {

	public ColumnOutOfRange() {
		System.out.println("Column number not allowed!");
	}
	
}
