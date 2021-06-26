package customException;

public class FullColumnException extends Throwable{
	
	private String errorString;

	public FullColumnException() {
		errorString = "The column is full! Please try inserting the token in another allowed, not-full column!";
	}
	
	public String getError() {
		return errorString;
	}
}
