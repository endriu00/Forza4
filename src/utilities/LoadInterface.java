package utilities;

import java.io.FileNotFoundException;

import customException.CorruptedFileException;
import customException.FullColumnException;
import gameComponents.Match;

public interface LoadInterface {

	public Match loadMatch(String name) throws FileNotFoundException, CorruptedFileException, FullColumnException;
}
