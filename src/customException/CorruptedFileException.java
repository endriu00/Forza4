package customException;

public class CorruptedFileException extends Throwable {

	private String errorString;
	
	public CorruptedFileException() {
		errorString = "File trying to read is corrupted.";
	}
	
	public String getError() {
		return errorString;
	}
}
