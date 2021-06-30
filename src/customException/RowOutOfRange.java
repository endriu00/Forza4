package customException;


public class RowOutOfRange extends Throwable {
	
	public RowOutOfRange() {
		System.out.println("Row number not allowed, because out of range!");
	}
}
