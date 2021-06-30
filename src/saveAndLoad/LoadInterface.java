package saveAndLoad;

import java.io.FileNotFoundException;

import customException.CorruptedFileException;
import gameComponents.Match;

/**
 * Interface for implementing the Observer design pattern in the GUI class.
 * 
 * @author andre
 *
 */
public interface LoadInterface {

	public Match loadMatch(String name) throws FileNotFoundException, CorruptedFileException;
}
