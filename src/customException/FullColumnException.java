package customException;

public class FullColumnException extends Throwable{

	public FullColumnException() {
		System.out.println("The column is full! Please try inserting the token in another allowed, not-full column!");
	}
}
