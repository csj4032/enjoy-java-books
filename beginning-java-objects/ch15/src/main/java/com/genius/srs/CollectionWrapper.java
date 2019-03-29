package com.genius.srs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class CollectionWrapper {

	private String pathToFile;

	public boolean initializeObjects(String pathToFile, boolean primary) {
		this.pathToFile = pathToFile;
		String line;
		boolean outcome = true;

		try (BufferedReader bIn = new BufferedReader(new FileReader(pathToFile))) {
			line = bIn.readLine();
			while (line != null) {
				if (primary) {
					parseData(line);
				} else {
					parseData2(line);
					line = bIn.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			outcome = false;
		} catch (IOException e) {
			outcome = false;
		}

		return outcome;
	}

	protected abstract void parseData2(String line);

	protected abstract void parseData(String line);
}
