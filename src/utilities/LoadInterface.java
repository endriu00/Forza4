package utilities;

import java.io.FileNotFoundException;

import gameComponents.Match;

public interface LoadInterface {

	public Match loadMatch(String name) throws FileNotFoundException;
}
